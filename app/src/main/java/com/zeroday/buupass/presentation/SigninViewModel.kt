package com.zeroday.buupass.presentation

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.zeroday.buupass.LogInResponse
import com.zeroday.buupass.R
import com.zeroday.buupass.RetrofitClient
import com.zeroday.buupass.loginDetails
import com.zeroday.buupass.model.UserResp
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SigninViewModel : ViewModel() {

    val emaiil: MutableState<String> = mutableStateOf("")
    val password: MutableState<String> = mutableStateOf("")
    val emailPlaceHolder: MutableState<String> = mutableStateOf("enter email")
    val passwordPlaceholder: MutableState<String> = mutableStateOf("enter password")

    val myResponse: MutableLiveData<Response<loginDetails>> = MutableLiveData()

    val auth = FirebaseAuth.getInstance()


    fun pushData(navController: NavController, activity: Context, progressDialog: ProgressDialog) {
        RetrofitClient.instance.signInUser(emaiil.value, password.value)
            .enqueue(object : Callback<LogInResponse> {
                override fun onResponse(
                    call: Call<LogInResponse>,
                    response: Response<LogInResponse>
                ) {

                    if (response.isSuccessful) {
                        Log.d("TAG", "onResponse: ${response.body()?.token}")

                        if (response.body()?.token != null) {
                            progressDialog.dismiss()

                            val bundle = Bundle()
                            bundle.putString("type", "api")

                            navController.navigate(R.id.toHome, bundle)

                        }

                    } else {
                        progressDialog.dismiss()
                        Toast.makeText(activity, "check Email/ Password", Toast.LENGTH_SHORT).show()
                    }

                }

                override fun onFailure(call: Call<LogInResponse>, t: Throwable) {
                    Log.d("TAG", "onFailure: ${t.message} ")
                    Log.d("TAG", "onFailure: Check your network connection")

                    Toast.makeText(activity, "Check your network connection", Toast.LENGTH_SHORT)
                        .show()
                }

            })


    }


    fun onEmailChange(email: String) {
        emaiil.value = email
    }

    fun onPasswordChange(passsword: String) {
        password.value = passsword
    }


    fun authenticateUser(
        navController: NavController,
        activity: Context,
        progressDialog: ProgressDialog
    ) {
        auth.signInWithEmailAndPassword(emaiil.value, password.value)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val bundle = Bundle()
                    bundle.putString("type", "fire")

                    navController.navigate(R.id.toHome, bundle)
                    progressDialog.dismiss()
                    Log.d("TAG", "authenticateUser: user logged in")
                } else {
                    Log.d("TAG", "authenticateUser: error Logging in")
                    progressDialog.dismiss()
                    Toast.makeText(activity, "check Email/ Password", Toast.LENGTH_SHORT).show()
                }
            }
    }
}