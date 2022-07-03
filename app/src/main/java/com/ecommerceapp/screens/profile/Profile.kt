package com.ecommerceapp.screens.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ecommerceapp.R
import com.ecommerceapp.component.*

@Composable
fun ProfileScreen(
    navController: NavHostController,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    AppContent(navController, title = stringResource(id = R.string.profile)) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            val profile = profileViewModel.profile.collectAsState().value

            val isEditModeOn = remember { mutableStateOf(false) }
            val nameState = remember { mutableStateOf(profile.name) }
            val genderState = remember { mutableStateOf(profile.gender) }
            val emailState = remember { mutableStateOf(profile.email) }
            val mobileState = remember { mutableStateOf(profile.mobile) }

            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    color = MaterialTheme.colors.background
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 50.dp),
                        color = Color(color = profile.bannerColor),
                        border = BorderStroke(width = 3.dp, color = Color.LightGray),
                        shape = RectangleShape,
                        elevation = 10.dp
                    ) {

                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        ProfileImage(image = profile.image)
                    }
                }

                Text(text = "Rating: ${profile.rating}", color = Color.Red)

                if (!isEditModeOn.value) {

                    InputFieldNonEditable(
                        value = profile.name,
                        valueState = nameState,
                        enabled = isEditModeOn.value,
                        isSingleLine = true,
                        imeAction = ImeAction.Next,
                        labelId = "Name"
                    )
                    InputFieldNonEditable(
                        value = profile.gender,
                        valueState = genderState,
                        enabled = isEditModeOn.value,
                        isSingleLine = true,
                        imeAction = ImeAction.Next,
                        labelId = "Gender"
                    )
                } else {
                    nameState.value = (profile.name)
                    genderState.value = (profile.gender)
                    InputField(
                        valueState = nameState,
                        enabled = isEditModeOn.value,
                        isSingleLine = true,
                        imeAction = ImeAction.Done,
                        labelId = "Gender"
                    )
                    InputField(
                        valueState = genderState,
                        enabled = isEditModeOn.value,
                        isSingleLine = true,
                        imeAction = ImeAction.Done,
                        labelId = "Gender"
                    )
                }

                InputFieldNonEditable(
                    value = profile.email,
                    valueState = emailState,
                    enabled = false,
                    isSingleLine = true,
                    imeAction = ImeAction.Done,
                    labelId = "Mobile"
                )

                InputFieldNonEditable(
                    value = profile.mobile,
                    valueState = mobileState,
                    enabled = false,
                    isSingleLine = true,
                    imeAction = ImeAction.Done,
                    labelId = "Mobile"
                )

                AppButton(
                    buttonname = if (!isEditModeOn.value)
                        "EDIT PROFILE"
                    else
                        "UPDATE"
                ) {
                    if (isEditModeOn.value) {
                        profileViewModel.updateProfile(
                            profile.copy(
                                image = "https://www.gethucinema.com/wp-content/uploads/2018/05/Pooja-Hegde-Chudi-Cute.jpg",
                                name = nameState.value,
                                gender = genderState.value,
                                email = profile.email,
                                mobile = profile.mobile,
                                rating = profile.rating,
                                bannerColor = 0xFF08380B
                            )
                        )
                    }
                    isEditModeOn.value = !isEditModeOn.value
                }
            }
        }
    }
}