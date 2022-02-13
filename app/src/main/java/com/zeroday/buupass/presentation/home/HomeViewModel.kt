package com.zeroday.buupass.presentation.home

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.zeroday.buupass.R
import com.zeroday.buupass.RetrofitClient
import com.zeroday.buupass.model.Car
import com.zeroday.buupass.model.UserResp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    val dealsList: MutableState<List<Car>> = mutableStateOf(
        listOf(
        Car(image = R.drawable.shelby, "Ford Shelby", "1,300", "Monthly"),
            Car(R.drawable.porche, "Porche Carrera", "480", "Daily"),
            Car(R.drawable.redmac, "Mclaren", "2,000", "Weekly"),
            Car(R.drawable.lambo, "Lamboghini", "450", "Daily")
        )
    )

    val availabledealsList: MutableState<List<Car>> = mutableStateOf(
        listOf(
            Car(image = R.drawable.shelby, "Ford Shelby", "1,300", "Monthly",height =  210.dp),
            Car(R.drawable.porche, "Porche Carrera", "480", "Daily",height =  230.dp),
            Car(R.drawable.redmac, "Mclaren", "2,000", "Weekly", height =  220.dp),
            Car(R.drawable.lambo, "Lamboghini", "450", "Daily", height =  240.dp),
            Car(R.drawable.bluemerc, "Mercedes Amg", "4850", "Monthly", height =  210.dp),
            Car(R.drawable.oldmustang, "Mustang", "3200", "Weekly", height =  230.dp),
            Car(R.drawable.yellowshel, "Chevrolet", "700", "Daily", height =  210.dp),
        )
    )


    val image = mutableStateOf("")

    fun getSingleUser(){
        RetrofitClient.instance.getUserDetails().enqueue(object : Callback<UserResp> {
            override fun onResponse(call: Call<UserResp>, response: Response<UserResp>) {
                if (response.code().equals(200)){

                    Log.d("TAG", "onResponse: ${response.body()?.data?.email}")

                    image.value = response.body()?.data?.avatar ?: ""
                }
            }

            override fun onFailure(call: Call<UserResp>, t: Throwable) {
                Log.d("TAG", "onFailure: check your internet connection")
            }

        })
    }

    fun clearData(){
        image.value = ""
    }

}