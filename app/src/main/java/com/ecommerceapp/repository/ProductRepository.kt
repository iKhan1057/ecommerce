package com.ecommerceapp.repository

import com.ecommerceapp.dao.ProductDao
import com.ecommerceapp.model.Product
import com.ecommerceapp.model.products.HomeProduct
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProductRepository @Inject constructor(private val productDao: ProductDao) {
   suspend fun insertAllProducts(product: ArrayList<HomeProduct>) = productDao.insertAllProducts(products = product)
    fun getAllProducts(): Flow<List<HomeProduct>> = productDao.getHomeProductList().flowOn(Dispatchers.IO)
        .conflate()
    suspend fun updateProduct(product: HomeProduct) = productDao.updateProduct(product = product)
}