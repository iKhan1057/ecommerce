package com.ecommerceapp.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ecommerceapp.R
import com.ecommerceapp.component.AppButton
import com.ecommerceapp.component.AppContent
import com.ecommerceapp.component.ProductsRow
import com.ecommerceapp.data.DataOrException
import com.ecommerceapp.model.ProductsList
import com.ecommerceapp.model.cart.CartProduct
import com.ecommerceapp.model.products.HomeProduct
import com.ecommerceapp.navigation.AppScreenName
import com.ecommerceapp.screens.cart.CartViewModel
import com.ecommerceapp.screens.profile.ProfileViewModel
import com.ecommerceapp.utils.UUIDConverter

@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val profile = profileViewModel.profile.collectAsState().value
    AppContent(
        navController,
        title = stringResource(id = R.string.home),
        profile = profile,
        isHome = true
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = Color.LightGray
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AppButton(buttonname = "GO TO CART"){
                    navController.navigate(AppScreenName.CART.name)
                }
                val productlist = homeViewModel.productlist.collectAsState().value
                if (productlist != null)
                    ProductsRow(productlist, navController, cartViewModel, onDelete = { item ->
                        cartViewModel.deleteCartProduct(
                            CartProduct(
                                productid = item.id,
                                category = item.category,
                                name = item.name,
                                photo = item.photo,
                                price = item.price,
                                unit = item.unit,
                                qty = item.product_quantity,
                                totalCost = (item.product_quantity * item.price.toDouble())
                            )
                        )
                    }) { item ->
                        cartViewModel.updateCartProduct(
                            CartProduct(
                                productid = item.id,
                                category = item.category,
                                name = item.name,
                                photo = item.photo,
                                price = item.price,
                                unit = item.unit,
                                qty = item.product_quantity,
                                totalCost = (item.product_quantity * item.price.toDouble())
                            )
                        )
                    }
                val productlistfromserver =
                    produceState<DataOrException<ProductsList, Boolean, Exception>>(
                        initialValue = DataOrException(
                            loading = true
                        )
                    ) {
                        value = homeViewModel.getProductListDataFromServer()
                    }.value

                if (productlistfromserver.loading == true) {
                    CircularProgressIndicator()
                } else {
                    val homeproduct = ArrayList<HomeProduct>()
                    for (items in productlistfromserver.data?.response_data?.product!!) {
                        homeproduct.add(
                            HomeProduct(
                                id = items.id,
                                name = items.name,
                                category = items.category,
                                photo = items.photo,
                                price = items.price,
                                unit = items.unit,
                                product_quantity = 0
                            )
                        )
                    }
                    homeViewModel.insertAllDataToDb(homeproduct)
                }
            }
        }
    }
}


