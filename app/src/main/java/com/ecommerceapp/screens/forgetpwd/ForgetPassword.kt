package com.ecommerceapp.screens.forgetpwd

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.ecommerceapp.R
import com.ecommerceapp.component.*

@Composable
fun ForgetPasswordScreen(navController: NavHostController) {
    AppComponentPreLogin(
        navController = navController,
        title = stringResource(id = R.string.forgetpwd)
    ) {
        val context = LocalContext.current

        val email = remember {
            mutableStateOf("")
        }
        val isValid = remember {
            email.value.isNotEmpty() && email.value.contains("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\$")
        }

        EmailInput(emailState = email)
        AppButton(buttonname = stringResource(id = R.string.get_otp)) {
            if (isValid)
                Toast.makeText(
                    context,
                    "An OTP has been send to your registered email id.",
                    Toast.LENGTH_LONG
                ).show()
            else
                Toast.makeText(
                    context,
                    "Please enter a valid email.",
                    Toast.LENGTH_LONG
                ).show()
        }
    }
}