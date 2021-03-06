package com.ecommerceapp.screens.cms


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.ecommerceapp.R
import com.ecommerceapp.component.AppContent
import com.ecommerceapp.component.loadWebUrl
import com.ecommerceapp.utils.AppUtils

@Composable
fun ContactUsScreen(navController: NavHostController) {
    AppContent(navController, title = stringResource(id = R.string.contactus)) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                loadWebUrl(AppUtils.local_privacy_url)
            }
        }
    }
}
