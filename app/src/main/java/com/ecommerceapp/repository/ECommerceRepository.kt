package com.ecommerceapp.repository

import android.util.Log
import com.ecommerceapp.data.DataOrException
import com.ecommerceapp.model.ProductsList
import com.ecommerceapp.network.EcommerceApi
import javax.inject.Inject

class ECommerceRepository @Inject constructor(private val ecommerceApi: EcommerceApi) {
    suspend fun getAllProducts(): DataOrException<ProductsList, Boolean, Exception> {
        val response = try {
            ecommerceApi.getProductList()
        } catch (execption: Exception) {
            Log.d("REX", "getProductList: $execption")
            return DataOrException(e = execption)
        }
        return DataOrException(data = response)
    }
}