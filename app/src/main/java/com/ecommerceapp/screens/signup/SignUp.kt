package com.ecommerceapp.screens.signup

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
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
import com.ecommerceapp.component.*
import com.ecommerceapp.model.profile.Profile
import com.ecommerceapp.navigation.AppScreenName
import com.ecommerceapp.screens.profile.ProfileViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(
    navController: NavHostController,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    AppComponentPreLogin(
        navController = navController,
        title = stringResource(id = R.string.signup),
        showBack = true
    ) {
        val scope = rememberCoroutineScope()
        val context = LocalContext.current
        val mobileState = remember { mutableStateOf("") }
        val usernameState = remember { mutableStateOf("") }
        val email = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        val passwordVisibility = remember { mutableStateOf(false) }
        val confirmPassword = remember { mutableStateOf("") }
        val confirmPasswordVisibility = remember { mutableStateOf(false) }

        val isValid = remember {
            email.value.isNotEmpty()
//                  &&  email.value.contains("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\$")
                    && password.value.isNotEmpty()
                    && password.value.length > 6
        }

        InputField(
            valueState = usernameState,
            enabled = true,
            isSingleLine = true,
            imeAction = ImeAction.Next,
            labelId = "Username"
        )
        AppDivider()

        InputField(
            valueState = mobileState,
            enabled = true,
            isSingleLine = true,
            imeAction = ImeAction.Next,
            labelId = "Mobile"
        )
        AppDivider()

        EmailInput(emailState = email)

        AppDivider()

        PasswordInput(
            passwordState = password,
            labelId = "Password",
            enabled = true,
            passwordVisibility = passwordVisibility,
            imeAction = ImeAction.Done
        )

        AppDivider()

        PasswordInput(
            passwordState = confirmPassword,
            labelId = "Confirm Password",
            enabled = true,
            passwordVisibility = confirmPasswordVisibility,
            imeAction = ImeAction.Done
        )
        AppDivider()
        TermsAndPrivacy(navController = navController)
        AppDivider()
        AppButton(
            buttonname = stringResource(id = R.string.signup),
            modifier = Modifier.padding(5.dp)
        ) {
            if (!isValid)
                scope.launch {
                    navController.navigate(AppScreenName.VERIFYOTP.name)
                }
            else
                Toast.makeText(
                    context,
                    "Please enter a valid details.",
                    Toast.LENGTH_LONG
                ).show()
        }
        AppDivider()
        AppButton(
            buttonname = stringResource(id = R.string.signin_now),
            modifier = Modifier.padding(5.dp)
        ) {
            navController.popBackStack()
        }

        AppDivider()
        AppButton(
            buttonname = stringResource(id = R.string.home),
            modifier = Modifier.padding(5.dp)
        ) {
            scope.launch {
                navController.navigate(AppScreenName.HOME.name)
            }
        }
    }
}


@Composable
fun TermsAndPrivacy(navController: NavHostController){
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = MaterialTheme.colors.primary)) {
            append("By joining, you have read and agreed to our\n")
        }

        pushStringAnnotation(tag = "policy", annotation = "")
        withStyle(style = SpanStyle(color = MaterialTheme.colors.primary, fontWeight = FontWeight.Bold)) {
            append("Privacy Policy")
        }
        pop()

        withStyle(style = SpanStyle(color = MaterialTheme.colors.primary)) {
            append(" and ")
        }

        pushStringAnnotation(tag = "terms",annotation = "")
        withStyle(style = SpanStyle(color = MaterialTheme.colors.primary, fontWeight = FontWeight.Bold)) {
            append("Terms & Conditions.")
        }
        pop()
    }

    ClickableText(text = annotatedString, style = TextStyle(fontSize = 16.sp, textAlign = TextAlign.Center), modifier = Modifier.fillMaxWidth().padding(10.dp),onClick = { offset ->
        annotatedString.getStringAnnotations(tag = "policy", start = offset, end = offset).firstOrNull()?.let {
            navController.navigate(AppScreenName.PRIVACY_POLICY.name)
        }

        annotatedString.getStringAnnotations(tag = "terms", start = offset, end = offset).firstOrNull()?.let {
            navController.navigate(AppScreenName.TERMS_AND_CONDITIONS.name)
        }
    })
}