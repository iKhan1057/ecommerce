package com.ecommerceapp.screens.address

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.ecommerceapp.R
import com.ecommerceapp.component.AppButton
import com.ecommerceapp.component.AppContent
import com.ecommerceapp.component.CustomDialog
import com.ecommerceapp.model.address.Address
import com.ecommerceapp.navigation.AppScreenName

@Composable
fun AddressScreen(
    navController: NavHostController,
    addressViewModel: AddressViewModel = hiltViewModel()
) {
    val openDialogCustom = remember { mutableStateOf(false) }
    if (openDialogCustom.value) {
        CustomDialog(openDialogCustom = openDialogCustom,
            title = "Delete",
            details = "Do you want to delete all your delivery addresses?",
            icon = Icons.Default.DeleteForever,
            discardtext = "Not Now",
            accepttext = "Remove",
            onDiscard = {}) {
            addressViewModel.deleteAllAddress()
        }
    }

    AppContent(navController, title = stringResource(id = R.string.address)) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val addresslists = addressViewModel.addresslist.collectAsState().value
                showAddress(
                    navController,
                    addresslists,
                    onAddAddress = { /*addressViewModel.addAddress(address = it)*/ },
                    onDelAddress = { openDialogCustom.value = true },
                    onAddressClick = { navController.navigate(AppScreenName.ADDEDITADDRESS.name + "/${it._id}") })
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun showAddress(
    navController: NavHostController,
    addresslists: List<Address>,
    onAddAddress: (address: Address) -> Unit = {},
    onDelAddress: () -> Unit = {},
    onAddressClick: (address: Address) -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(all = 20.dp)
    ) {
        AppButton(buttonname = "ADD ADDRESS") {
            navController.navigate(AppScreenName.ADDEDITADDRESS.name)
//            onAddAddress(
//                Address(
//                    addressname = "Home",
//                    address1 = "Bolai Dutta Street",
//                    address2 = "MD JAN",
//                    pincode = "700073",
//                    city = "Kolkata",
//                    state = "West Bengal",
//                    country = "India",
//                    mobile = "9878953256",
//                    userid = "1",
//                    username = "Imran"
//                )
//            )
        }
        AppButton(buttonname = "DELETE ALL ADDRESS") {
            onDelAddress()
        }
        LazyColumn() {
            items(items = addresslists) { item: Address ->
                Card(
                    onClick = { onAddressClick(item) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    elevation = 5.dp,
                    backgroundColor = if (item.address_default) MaterialTheme.colors.onSecondary else MaterialTheme.colors.background,
                    shape = RoundedCornerShape(5.dp)
                ) {
                    Column(modifier = Modifier.padding(all = 10.dp)) {
//                        Text(text = "Address ID:${item._id}", color = Color.Red)

                        Text(
                            text = item.addressname,
                            color = MaterialTheme.colors.primary,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 20.sp
                        )
                        Text(
                            text = item.username,
                            color = MaterialTheme.colors.primary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Text(
                            text = item.mobile,
                            color = MaterialTheme.colors.primary,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp
                        )

                        Text(
                            text = item.address1 + ", " + item.address2 + ", " + item.city + ", " + item.state + ", " + item.country + ", " + item.pincode,
                            color = MaterialTheme.colors.primary,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp
                        )
//                        Text(
//                            text = item.address2,
//                            color = MaterialTheme.colors.primary,
//                            fontWeight = FontWeight.Normal,
//                            fontSize = 14.sp
//                        )
//                        Text(
//                            text = item.city,
//                            color = MaterialTheme.colors.primary,
//                            fontWeight = FontWeight.Normal,
//                            fontSize = 14.sp
//                        )
//                        Text(
//                            text = item.state,
//                            color = MaterialTheme.colors.primary,
//                            fontWeight = FontWeight.Normal,
//                            fontSize = 14.sp
//                        )
//                        Text(
//                            text = item.country,
//                            color = MaterialTheme.colors.primary,
//                            fontWeight = FontWeight.Normal,
//                            fontSize = 14.sp
//                        )
//                        Text(
//                            text = "Pincode:${item.pincode}",
//                            color = MaterialTheme.colors.primary,
//                            fontWeight = FontWeight.Normal,
//                            fontSize = 14.sp
//                        )
//                        Text(text = "Address Userid:${item.userid}", color = Color.Red)
                    }
                }
            }
        }
    }
}

