package com.ecommerceapp.screens.home

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ecommerceapp.R
import com.ecommerceapp.component.AppButton
import com.ecommerceapp.component.ScreenGreeting
import com.ecommerceapp.navigation.AppScreenName

@Composable
fun HomeScreen(navController: NavController) {
    val activity = (LocalContext.current as? Activity)
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
            ScreenGreeting(name = stringResource(id = R.string.home)) {
                activity?.finish()
            }

            Divider(modifier = Modifier.height(12.dp))
            AppButton(buttonname = "Profile") {
                navController.navigate(AppScreenName.PROFILE.name)
            }

            Divider(modifier = Modifier.height(12.dp))
            AppButton(buttonname = stringResource(id = R.string.changepwd)) {
                navController.navigate(AppScreenName.CHANGEPASSWORD.name)
            }

            Divider(modifier = Modifier.height(12.dp))
            AppButton(buttonname = stringResource(id = R.string.cart)) {
                navController.navigate(AppScreenName.CART.name)
            }
        }
    }
}