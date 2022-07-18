package com.ecommerceapp.screens.cart

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.ecommerceapp.R
import com.ecommerceapp.component.AppButton
import com.ecommerceapp.component.AppContent
import com.ecommerceapp.component.CustomDialog
import com.ecommerceapp.component.CustomDialogUI
import com.ecommerceapp.model.cart.CartProduct
import com.ecommerceapp.model.cart.TotalCost
import com.ecommerceapp.navigation.AppScreenName

@Composable
fun CartScreen(
    navController: NavHostController,
    cartViewModel: CartViewModel = hiltViewModel()
) {
    AppContent(navController, title = stringResource(id = R.string.cart)) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = Color.LightGray
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val cartProduct = cartViewModel.cartProduct.collectAsState().value
                if (cartProduct == null) {
                    CircularProgressIndicator()
                } else {
                    val cartProductTotalCost = cartViewModel.getTotalCost().collectAsState().value

                    AppButton(buttonname = "Total: ${cartProductTotalCost.totalprice}") {
                        navController.navigate(AppScreenName.ADDRESS.name)
                    }

                    ShowCartProducts(
                        cartProduct,
                        navController,
                        onDelete = { cartViewModel.deleteCartProduct(it) }) {
                        cartViewModel.updateCartProduct(cartProduct = it)
                    }
                }
            }
        }
    }
}

@Composable
fun ShowCartProducts(
    cartProduct: List<CartProduct>,
    navController: NavHostController,
    onDelete: (CartProduct) -> Unit = {},
    onUpdate: (CartProduct) -> Unit = {}
) {
    val context = LocalContext.current
    LazyColumn(modifier = Modifier.padding(1.dp)) {
        items(items = cartProduct) { item: CartProduct ->
            Card(
                modifier = Modifier
                    .padding(5.dp),
                elevation = 5.dp,
                backgroundColor = MaterialTheme.colors.background,
                shape = RectangleShape,
                border = BorderStroke(width = 1.dp, color = Color.Transparent)
            ) {
                Row(
                    modifier = Modifier
                        .clickable {
                            Toast
                                .makeText(context, item.name.toString(), Toast.LENGTH_LONG)
                                .show()
                        },
                ) {
                    AsyncImage(
                        model = item.photo,
                        contentDescription = "product image",
                        modifier = Modifier
                            .size(80.dp)
                            .padding(2.dp),
                        contentScale = ContentScale.FillBounds
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 4.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = item.name,
                            modifier = Modifier.padding(1.dp),
                            color = Color.Red,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Text(
                            text = "Rs" + item.price + "/" + item.unit,
                            modifier = Modifier.padding(1.dp),
                            color = Color.Red,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal
                        )
                        Text(
                            text = "Total: Rs ${item.totalCost}",
                            modifier = Modifier.padding(1.dp),
                            color = Color.Red,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal
                        )

                        //====Bottom Row==========//
                        CartProcuctUnitCal(onUpdate, item, onDelete)

                    }
                }
            }
        }
    }
}

@Composable
private fun CartProcuctUnitCal(
    onUpdate: (CartProduct) -> Unit,
    item: CartProduct,
    onDelete: (CartProduct) -> Unit
) {
    val openDialogCustom = remember { mutableStateOf(false) }
    if (openDialogCustom.value) {
        CustomDialog(openDialogCustom = openDialogCustom,
            title = "Remove",
            details = "Do you want to remove ${item.name} from your cart?",
            icon = Icons.Default.DeleteForever,
            discardtext= "Not Now",
            accepttext= "Remove",
            onDiscard = {}) {
            onDelete(item)
        }
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        //====Quantity section====//
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.AddBox,
                contentDescription = "Delete",
                tint = Color.Red,
                modifier = Modifier
                    .size(35.dp)
                    .clickable {
                        onUpdate(
                            item.copy(
                                qty = item.qty + 1,
                                totalCost = (item.price.toDouble() * (item.qty + 1))
                            )
                        )
                    })

            Text(
                text = item.qty.toString(),
                modifier = Modifier.padding(start = 5.dp, end = 5.dp),
                color = Color.Red,
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Icon(imageVector = Icons.Default.IndeterminateCheckBox,
                contentDescription = "Delete",
                tint = Color.Red,
                modifier = Modifier
                    .size(35.dp)
                    .clickable {
                        if (item.qty > 1) onUpdate(
                            item.copy(
                                qty = item.qty - 1,
                                totalCost = (item.price.toDouble() * (item.qty - 1))
                            )
                        )
                        else onDelete(item)
                    })
        }
        //====Quantity section END====//
        val context = LocalContext.current
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
                tint = Color.Red,
                modifier = Modifier
                    .size(35.dp)
                    .clickable(onClick = {
                        openDialogCustom.value = true
                    })
            )
        }
    }
}
