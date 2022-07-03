package com.ecommerceapp.screens.address

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.ecommerceapp.R
import com.ecommerceapp.component.AppButton
import com.ecommerceapp.component.AppContent
import com.ecommerceapp.model.address.Address
import com.ecommerceapp.navigation.AppScreenName

@Composable
fun AddressScreen(
    navController: NavHostController,
    addressViewModel: AddressViewModel = hiltViewModel()
) {
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
                    onAddAddress = { addressViewModel.addAddress(address = it) },
                    onDelAddress = { addressViewModel.deleteAllAddress() },
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
        AppButton(buttonname ="ADD ADDRESS"){
            onAddAddress(
                Address(
                    addressname = "Home",
                    address1 = "Bolai Dutta Street",
                    address2 = "MD JAN",
                    pincode = "700073",
                    city = "Kolkata",
                    state = "West Bengal",
                    country = "India",
                    mobile = "9878953256",
                    userid = "1",
                    username = "Imran"
                )
            )
        }
        AppButton(buttonname ="DELETE ALL ADDRESS"){
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
                    backgroundColor = MaterialTheme.colors.background,
                    shape = RoundedCornerShape(5.dp)
                ) {
                    Column(modifier = Modifier.padding(all = 10.dp)) {
                        Text(text = "Address ID:${item._id}", color = Color.Red)
                        Text(text = "Address Name:${item.addressname}", color = Color.Red)
                        Text(text = "Address Address1:${item.address1}", color = Color.Red)
                        Text(text = "Address Address2:${item.address2}", color = Color.Red)
                        Text(text = "Address City:${item.city}", color = Color.Red)
                        Text(text = "Address State:${item.state}", color = Color.Red)
                        Text(text = "Address Country:${item.country}", color = Color.Red)
                        Text(text = "Address Pincode:${item.pincode}", color = Color.Red)
                        Text(text = "Address Mobile:${item.mobile}", color = Color.Red)
                        Text(text = "Address Username:${item.username}", color = Color.Red)
                        Text(text = "Address Userid:${item.userid}", color = Color.Red)
                    }
                }
            }
        }
    }
}

