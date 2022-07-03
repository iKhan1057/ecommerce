package com.ecommerceapp.screens.changepwd

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.navigation.NavHostController
import com.ecommerceapp.R
import com.ecommerceapp.component.AppButton
import com.ecommerceapp.component.AppComponentPreLogin
import com.ecommerceapp.component.AppContent
import com.ecommerceapp.component.PasswordInput
import com.ecommerceapp.navigation.AppScreenName

@Composable
fun ChangePasswordScreen(navController: NavHostController) {
    AppContent(navController, title = stringResource(id = R.string.changepwd)) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState(0))
            ) {

                val oldPassword = remember {
                    mutableStateOf("")
                }
                val oldPasswordVisibility = remember {
                    mutableStateOf(false)
                }
                PasswordInput(
                    passwordState = oldPassword,
                    labelId = "Old Password",
                    enabled = true,
                    passwordVisibility = oldPasswordVisibility,
                    imeAction = ImeAction.Next
                )


                val newPassword = remember {
                    mutableStateOf("")
                }
                val newPasswordVisibility = remember {
                    mutableStateOf(false)
                }
                PasswordInput(
                    passwordState = newPassword,
                    labelId = "New Password",
                    enabled = true,
                    passwordVisibility = newPasswordVisibility,
                    imeAction = ImeAction.Next
                )


                val confirmPassword = remember {
                    mutableStateOf("")
                }
                val confirmPasswordVisibility = remember {
                    mutableStateOf(false)
                }
                PasswordInput(
                    passwordState = confirmPassword,
                    labelId = "Confirm Password",
                    enabled = true,
                    passwordVisibility = confirmPasswordVisibility,
                    imeAction = ImeAction.Done
                )
                /**
                 * Need to add the hint to help user understand the criteria
                 * Password Length should be of 6 char
                 * Password should have one Capital Letter
                 * Password should have one Special Char
                 * New Password should be different from Old Password
                 * **/
                val isValid = remember {
                    oldPassword.value.isNotEmpty() &&
                            newPassword.value.isNotEmpty() &&
                            confirmPassword.value.isNotEmpty() &&
                            newPassword.value == confirmPassword.value &&
                            newPassword.value != oldPassword.value
                }
                val context = LocalContext.current
                AppButton(buttonname = "Change Password") {
                    if (isValid)
                        Toast.makeText(
                            context,
                            "OTP has been send to your registered Mobile and Email",
                            Toast.LENGTH_LONG
                        ).show()
                    else
                        Toast.makeText(context, "Check your input", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}