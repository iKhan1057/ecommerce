package com.ecommerceapp.screens.verification

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ecommerceapp.R
import com.ecommerceapp.component.AppButton
import com.ecommerceapp.component.AppComponentPreLogin
import com.ecommerceapp.component.AppDivider
import com.ecommerceapp.component.InputField
import com.ecommerceapp.model.profile.Profile
import com.ecommerceapp.navigation.AppScreenName
import com.ecommerceapp.screens.profile.ProfileViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun VerifyOTPCode(
    navController: NavHostController,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    AppComponentPreLogin(
        navController = navController,
        title = stringResource(id = R.string.verify_otp)
    ) {
        val scope = rememberCoroutineScope()

        Text(text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colors.primary,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp
                )
            ) {
                append("Verify the OTP send to your registered")
            }


            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colors.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            ) {
                append(" Email ID")
            }

            append(" and ")


            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colors.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            ) {
                append("Mobile Number.")
            }

        }, modifier = Modifier.fillMaxWidth().padding(all = 10.dp), textAlign = TextAlign.Center)
        val mobileOTPState = remember {
            mutableStateOf("")
        }
        AppDivider(modifier = Modifier.height(30.dp))
        InputField(
            valueState = mobileOTPState,
            enabled = true,
            isSingleLine = true,
            imeAction = ImeAction.Next,
            labelId = "Mobile OTP"
        )

        val emailOTPState = remember {
            mutableStateOf("")
        }
        AppDivider()
        InputField(
            valueState = emailOTPState,
            enabled = true,
            isSingleLine = true,
            imeAction = ImeAction.Done,
            labelId = "Email OTP"
        )
        AppDivider()

        AppButton(buttonname = "Verify OTP", modifier = Modifier.padding(5.dp)) {
            scope.launch {
                profileViewModel.addProfile(
                    profile = Profile(
                        id = "1",
                        name = "Imran Khan",
                        gender = "Male",
                        email = "ikhan@gmail.com",
                        image = "https://www.business2community.com/wp-content/uploads/2017/08/blank-profile-picture-973460_640.png",
                        mobile = "9878523589",
                        rating = 5,
                        bannerColor = 0xFFD3D3D3
                    )
                )
                delay(500)
                navController.navigate(AppScreenName.HOME.name)
            }
        }

    }
}