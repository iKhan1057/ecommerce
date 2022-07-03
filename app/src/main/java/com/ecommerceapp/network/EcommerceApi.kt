package com.ecommerceapp.network

import com.ecommerceapp.model.ProductsList
import retrofit2.http.GET

interface EcommerceApi {
    @GET("iKhan1057/ecommerce/main/foodproducts.json")
    suspend fun getProductList():ProductsList
}