package com.zeroday.buupass.presentation

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.zeroday.buupass.components.SignInScreen
import com.zeroday.buupass.loginDetails
import kotlinx.coroutines.CoroutineScope

class SignInFragment : Fragment() {

    val viewModel: SigninViewModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {

                val email = viewModel.emaiil.value
                val password = viewModel.password.value
                val emailPlace = viewModel.emailPlaceHolder.value
                val passwordPlace = viewModel.passwordPlaceholder.value

                val progressDialog = ProgressDialog(activity)
                progressDialog.setMessage("Signing in...")
                progressDialog.setCancelable(false)



                SignInScreen(
                    email = email,
                    onEmailChange = viewModel::onEmailChange,
                    emailPlaceholder = emailPlace,
                    password = password,
                    onPasswordChange = viewModel::onPasswordChange,
                    passwordPlaceholder = passwordPlace,
                    pushData = {
                        if (!email.equals("")) {
                            if (!password.equals("")) {

                                activity?.let {

                                    progressDialog.show()

                                    viewModel.pushData(
                                        findNavController(),
                                        activity = it,
                                        progressDialog = progressDialog
                                    )
                                }
                            } else {
                                Toast.makeText(
                                    activity,
                                    "Password field is empty",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            Toast.makeText(activity, "Email field is empty", Toast.LENGTH_SHORT)
                                .show()
                        }

                    },
                    authenticateUserF = {
                        progressDialog.show()
                        activity?.let {
                            viewModel.authenticateUser(
                                findNavController(),
                                activity = it,
                                progressDialog = progressDialog
                            )
                        }
                    }
                )

            }
        }
    }
}