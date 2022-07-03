package com.ecommerceapp.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.ecommerceapp.model.Product
import com.ecommerceapp.model.cart.CartProduct
import com.ecommerceapp.model.products.HomeProduct
import com.ecommerceapp.screens.cart.CartViewModel

@Composable
fun ProductsRow(
    productlist: List<HomeProduct>,
    navController: NavHostController,
    cartViewModel: CartViewModel,
    onDelete: (HomeProduct) -> Unit = {},
    onUpdate: (HomeProduct) -> Unit = {}
) {

    productlist.let { productslist ->
        LazyColumn() {
            items(items = productslist) { item: HomeProduct ->
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
                            .padding(5.dp)
                            .background(color = MaterialTheme.colors.background)
                            .clickable {

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
                            //====Quantity section====//
                            if (item.product_quantity == 0) {
                                AddRow(cartViewModel, item)
                            } else {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End,
                                    verticalAlignment = CenterVertically
                                ) {
                                    AppButton(buttonname = "+") {
                                        onUpdate(item.copy(product_quantity = item.product_quantity + 1))
                                    }
                                    Text(
                                        text = item.product_quantity.toString(),
                                        modifier = Modifier.padding(start = 5.dp, end = 5.dp),
                                        color = Color.Red
                                    )
                                    AppButton(buttonname = "-") {
                                        if (item.product_quantity > 1) onUpdate(
                                            item.copy(
                                                product_quantity = item.product_quantity - 1
                                            )
                                        )
                                        else onDelete(item)
                                    }
                                }
                                //====Quantity section END====//
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AddRow(cartViewModel: CartViewModel, item: HomeProduct) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        AppButton(buttonname = "Add", modifier = Modifier.padding(0.dp)) {
            cartViewModel.addCartProduct(
                CartProduct(
                    productid = item.id,
                    category = item.category,
                    name = item.name,
                    photo = item.photo,
                    price = item.price,
                    unit = item.unit,
                    qty = 1,
                    totalCost = (item.price.toDouble())
                )
            )
        }
    }
}
