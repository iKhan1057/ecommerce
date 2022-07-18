package com.ecommerceapp.screens.signin

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ecommerceapp.R
import com.ecommerceapp.component.*
import com.ecommerceapp.navigation.AppScreenName

@Composable
fun SignInScreen(navController: NavHostController) {
    AppComponentPreLogin(
        navController = navController,
        title = stringResource(id = R.string.signin),
        showBack = false
    ) {
        val context = LocalContext.current

        val email = remember {
            mutableStateOf("")
        }
        val password = remember {
            mutableStateOf("")
        }
        val passwordVisibility = remember {
            mutableStateOf(false)
        }
        val isValid = remember {
            mutableStateOf(email.value.isNotEmpty()
                    && password.value.isNotEmpty()
                    && password.value.length > 6)
        }
//                  &&  email.value.contains("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\$")
        EmailInput(emailState = email)

        AppDivider()

        PasswordInput(
            passwordState = password,
            labelId = "Password",
            enabled = true,
            passwordVisibility = passwordVisibility,
            imeAction = ImeAction.Done
        )

        AppButton(buttonname = stringResource(id = R.string.signin), modifier = Modifier.padding(5.dp)) {
//            if (isValid.value)
               navController.navigate(AppScreenName.HOME.name)
//            else
//                Toast.makeText(
//                    context,
//                    "Please enter a valid details.",
//                    Toast.LENGTH_LONG
//                ).show()
        }

        AppDivider()
        AppButton(buttonname = stringResource(id = R.string.forgetpwd), modifier = Modifier.padding(5.dp)) {
            navController.navigate(AppScreenName.FORGETPASSWORD.name)
        }

        AppDivider()
        AppButton(buttonname = stringResource(id = R.string.signup_now),
            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp,start = 20.dp,end = 20.dp)) {
            navController.navigate(AppScreenName.SIGNUP.name)
        }
    }
}