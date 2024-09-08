package com.ecommerceapp.screens.cms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ecommerceapp.AppSwitch
import com.ecommerceapp.AppText
import com.ecommerceapp.R
import com.ecommerceapp.component.AppButton
import com.ecommerceapp.component.AppContent
import com.ecommerceapp.component.AppDivider
import com.ecommerceapp.navigation.AppScreenName

@Composable
fun SettingsScreen(navController: NavHostController) {
    AppContent(navController, title = stringResource(id = R.string.settings)) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 10.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    AppText(text = stringResource(id = R.string.shownoti), isUpperCase = false)
                    AppSwitch()
                }

                AppDivider()

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 10.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    AppText(text = stringResource(id = R.string.primeuser), isUpperCase = false)
                    AppSwitch()
                }

                AppDivider()

                AppButton(buttonname = stringResource(id = R.string.changepwd)) {
                    navController.navigate(AppScreenName.CHANGEPASSWORD.name)
                }

            }
        }
    }
}