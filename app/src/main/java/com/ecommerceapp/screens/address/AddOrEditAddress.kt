package com.ecommerceapp.screens.address

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ecommerceapp.R
import com.ecommerceapp.component.AppButton
import com.ecommerceapp.component.AppContent
import com.ecommerceapp.component.InputField
import com.ecommerceapp.utils.UUIDConverter
import kotlinx.coroutines.launch


@Composable
fun AddOrEditAddressScreen(
    navController: NavHostController,
    viewModel: AddressViewModel = hiltViewModel(),
    addressId: String
) {
    AppContent(navController, title = stringResource(id = R.string.addoreditaddress)) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            val address = viewModel.getSingleAddress(UUIDConverter.uuidFromString(addressId))
                .collectAsState().value
            val isEditModeOn = remember { mutableStateOf(false) }
            val addressNameState = remember { mutableStateOf(address.addressname) }
            val address1State = remember { mutableStateOf(address.address1) }
            val address2State = remember { mutableStateOf(address.address2) }
            val cityState = remember { mutableStateOf(address.city) }
            val stateState = remember { mutableStateOf(address.state) }
            val countryState = remember { mutableStateOf(address.country) }
            val pinState = remember { mutableStateOf(address.pincode) }
            val mobileState = remember { mutableStateOf(address.mobile) }
            val usernameState = remember { mutableStateOf(address.username) }

            addressNameState.value = address.addressname
            address1State.value = address.address1
            address2State.value = address.address2
            cityState.value = address.city
            stateState.value = address.state
            countryState.value = address.country
            pinState.value = address.pincode
            mobileState.value = address.mobile
            usernameState.value = address.username

            Column(
                modifier = Modifier
                    .padding(all = 10.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState(0)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                InputField(
                    valueState = addressNameState,
                    enabled = isEditModeOn.value,
                    isSingleLine = true,
                    imeAction = ImeAction.Next,
                    labelId = "Address Type"
                )
                InputField(
                    valueState = address1State,
                    enabled = isEditModeOn.value,
                    isSingleLine = true,
                    imeAction = ImeAction.Next,
                    labelId = "Address Line 1"
                )
                InputField(
                    valueState = address2State,
                    enabled = isEditModeOn.value,
                    isSingleLine = true,
                    imeAction = ImeAction.Next,
                    labelId = "Address Line 2"
                )
                InputField(
                    valueState = cityState,
                    enabled = isEditModeOn.value,
                    isSingleLine = true,
                    imeAction = ImeAction.Next,
                    labelId = "City"
                )
                InputField(
                    valueState = stateState,
                    enabled = isEditModeOn.value,
                    isSingleLine = true,
                    imeAction = ImeAction.Next,
                    labelId = "State"
                )
                InputField(
                    valueState = countryState,
                    enabled = isEditModeOn.value,
                    isSingleLine = true,
                    imeAction = ImeAction.Next,
                    labelId = "Country"
                )
                InputField(
                    valueState = pinState,
                    enabled = isEditModeOn.value,
                    isSingleLine = true,
                    imeAction = ImeAction.Next,
                    labelId = "PinCode"
                )
                InputField(
                    valueState = usernameState,
                    enabled = isEditModeOn.value,
                    isSingleLine = true,
                    imeAction = ImeAction.Next,
                    labelId = "Username"
                )
                InputField(
                    valueState = mobileState,
                    enabled = isEditModeOn.value,
                    isSingleLine = true,
                    imeAction = ImeAction.Next,
                    labelId = "Mobile"
                )
                AppButton(
                    buttonname = if (!isEditModeOn.value)
                        "EDIT ADDRESS"
                    else
                        "UPDATE ADDRESS"
                ) {
                    if (isEditModeOn.value)
                        viewModel.updateAddress(
                            address.copy(
                                _id = address._id,
                                addressname = addressNameState.value,
                                address1 = address1State.value,
                                address2 = address2State.value,
                                pincode = pinState.value,
                                city = cityState.value,
                                state = stateState.value,
                                country = countryState.value,
                                mobile = mobileState.value,
                                userid = address.userid,
                                username = usernameState.value
                            )
                        )
                    isEditModeOn.value = !isEditModeOn.value
                }

                val scope = rememberCoroutineScope()
                Spacer(modifier = Modifier.height(5.dp))

                AppButton(buttonname = "DELETE ADDRESS") {
                    scope.launch {
                        viewModel.deleteAddress(address)
                        navController.popBackStack()
                    }
                }
            }
        }
    }
}

