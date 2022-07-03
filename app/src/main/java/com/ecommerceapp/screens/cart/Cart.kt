package com.ecommerceapp.screens.cart

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.ecommerceapp.model.cart.CartProduct
import com.ecommerceapp.model.cart.TotalCost

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
            Card( modifier = Modifier
                .padding(5.dp),elevation = 5.dp, backgroundColor = MaterialTheme.colors.background,
            shape = RectangleShape, border = BorderStroke(width = 1.dp,color = Color.Transparent)
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
                        //====Quantity section====//
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(onClick = {
                                onUpdate(
                                    item.copy(
                                        qty = item.qty + 1,
                                        totalCost = (item.price.toDouble() * (item.qty + 1))
                                    )
                                )
                            }) {
                                Text(text = "+", fontWeight = FontWeight.ExtraBold)
                            }
                            Text(
                                text = item.qty.toString(),
                                modifier = Modifier.padding(start = 5.dp, end = 5.dp),
                                color = Color.Red
                            )
                            Button(onClick = {
                                if (item.qty > 1) onUpdate(
                                    item.copy(
                                        qty = item.qty - 1,
                                        totalCost = (item.price.toDouble() * (item.qty - 1))
                                    )
                                )
                                else onDelete(item)
                            }) {
                                Text(text = "-", fontWeight = FontWeight.ExtraBold)
                            }
                        }
                        //====Quantity section END====//
                    }
                }
            }
        }
    }
}
