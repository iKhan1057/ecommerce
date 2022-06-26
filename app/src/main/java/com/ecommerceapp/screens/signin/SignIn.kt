package com.ecommerceapp.screens.signin

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ecommerceapp.R
import com.ecommerceapp.component.AppButton
import com.ecommerceapp.component.ScreenGreeting
import com.ecommerceapp.navigation.AppScreenName

@Composable
fun SignInScreen(navController: NavController) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 10.dp),
        color = MaterialTheme.colors.background
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ScreenGreeting(name = stringResource(id = R.string.signin)) {
                navController.popBackStack()
            }

            Divider(modifier = Modifier.height(10.dp))
            AppButton(buttonname = stringResource(id = R.string.forgetpwd)) {
                navController.navigate(AppScreenName.FORGETPASSWORD.name)
            }

            Divider(modifier = Modifier.height(10.dp))
            AppButton(buttonname = stringResource(id = R.string.signup)) {
                navController.navigate(AppScreenName.SIGNUP.name)
            }
        }
    }
}