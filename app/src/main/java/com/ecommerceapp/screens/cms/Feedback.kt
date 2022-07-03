package com.ecommerceapp.screens.cms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.navigation.NavHostController
import com.ecommerceapp.R
import com.ecommerceapp.component.AppButton
import com.ecommerceapp.component.AppContent
import com.ecommerceapp.component.FeedbackInputField

@Composable
fun FeedbackScreen(navController: NavHostController) {
    AppContent(navController, title = stringResource(id = R.string.feedback)) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val feedbackState = remember {
                    mutableStateOf("")
                }
                FeedbackInputField(
                    valueState = feedbackState,
                    enabled = true,
                    isSingleLine = false,
                    imeAction = ImeAction.Done,
                    labelId = "Enter your feedback"
                )

                AppButton(buttonname = "Submit") {
                    navController.popBackStack()
                }
            }
        }
    }
}