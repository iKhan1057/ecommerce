package com.ecommerceapp.screens.address

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ecommerceapp.R
import com.ecommerceapp.component.*
import com.ecommerceapp.model.address.Address
import com.ecommerceapp.utils.UUIDConverter
import kotlinx.coroutines.launch

@Composable
fun AddOrEditAddressScreen(
    navController: NavHostController,
    viewModel: AddressViewModel = hiltViewModel(),
    addressId: String?
) {
    val scope = rememberCoroutineScope()
    val openDialogCustom = remember { mutableStateOf(false) }


    AppContent(navController, title = stringResource(id = R.string.addoreditaddress)) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            val listofaddressname = remember {
                mutableStateOf(
                    arrayListOf(
                        "Select type",
                        "Home",
                        "Office",
                        "Others"
                    )
                )
            }

            var address = Address()
            val addAddress = remember { mutableStateOf(false) }
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
            val checkedState = remember { mutableStateOf(address.address_default) }

            val addressbyname = viewModel.getAddressByName().collectAsState().value

            val defaultAddress =
                viewModel.getAddressByDefault(default = true).collectAsState().value


            if (addressId.isNullOrEmpty()) {
                Log.d("TAG", "AddOrEditAddressScreen: ADDAddress")
                isEditModeOn.value = true
                addAddress.value = true
                if (!addressbyname.isNullOrEmpty()) {
                    Log.d("TAG", "AddOrEditAddressScreen: $addressbyname")
                    if (listofaddressname.value.isNotEmpty()) {
                        for (items in addressbyname) {
                            if (items.addressname == "Home")
                                listofaddressname.value.remove("Home")
                            if (items.addressname == "Office")
                                listofaddressname.value.remove("Office")
                        }
                    }
                }
            } else {
                address = viewModel.getSingleAddress(UUIDConverter.uuidFromString(addressId))
                    .collectAsState().value
                if (address != null) {
                    if (!addressbyname.isNullOrEmpty()) {
                        Log.d("TAG", "AddOrEditAddressScreen: $addressbyname")
                        if (listofaddressname.value.isNotEmpty()) {
                            for (items in addressbyname) {
                                if (items.addressname == "Home" && address.addressname != "Home")
                                    listofaddressname.value.remove("Home")
                                if (items.addressname == "Office" && address.addressname != "Office")
                                    listofaddressname.value.remove("Office")
                            }
                        }
                    }
                    addressNameState.value = address.addressname
                    address1State.value = address.address1
                    address2State.value = address.address2
                    cityState.value = address.city
                    stateState.value = address.state
                    countryState.value = address.country
                    pinState.value = address.pincode
                    mobileState.value = address.mobile
                    usernameState.value = address.username
                    checkedState.value =address.address_default
                }
            }
            if (openDialogCustom.value) {
                CustomDialog(openDialogCustom = openDialogCustom,
                    title = "Delete",
                    details = "Do you want to delete ${address.addressname} address?",
                    icon = Icons.Default.DeleteForever,
                    discardtext = "Not Now",
                    accepttext = "Delete",
                    onDiscard = {}) {
                    scope.launch {
                        viewModel.deleteAddress(address)
                        navController.popBackStack()
                    }
                }
            }
            Column(
                modifier = Modifier
                    .padding(all = 10.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState(0)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DropDown(
                    addresstypeState = addressNameState,
                    items = listofaddressname.value,
                    clickEnable = isEditModeOn.value,
                    label = "Address Type"
                ) {
                    addressNameState.value = it
                }

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

                CheckBoxField(details = "Set this as default address", checkedState = checkedState)

                if (addAddress.value) {
                    AppButton(buttonname = "ADD ADDRESS") {
                        scope.launch {
                            if (defaultAddress != null && checkedState.value)
                                viewModel.updateAddress(defaultAddress.copy(address_default = false))
                            viewModel.addAddress(
                                Address(
                                    addressname = addressNameState.value,
                                    address1 = address1State.value,
                                    address2 = address2State.value,
                                    pincode = pinState.value,
                                    city = cityState.value,
                                    state = stateState.value,
                                    country = countryState.value,
                                    mobile = mobileState.value,
                                    userid = "1",
                                    username = usernameState.value,
                                    address_default = checkedState.value
                                )
                            )
                            navController.popBackStack()
                        }
                    }
                } else {
                    AppButton(
                        buttonname = if (!isEditModeOn.value)
                            "EDIT ADDRESS"
                        else
                            "UPDATE ADDRESS"
                    ) {
                        if (defaultAddress != null && checkedState.value)
                            viewModel.updateAddress(defaultAddress.copy(address_default = false))
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
                                    username = usernameState.value,
                                    address_default = checkedState.value
                                )
                            )
                        isEditModeOn.value = !isEditModeOn.value
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    AppButton(buttonname = "DELETE ADDRESS") {
                        openDialogCustom.value = true
                    }
                }
            }
        }
    }
}


