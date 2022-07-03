package com.ecommerceapp.repository

import com.ecommerceapp.dao.CartDao
import com.ecommerceapp.model.cart.CartProduct
import com.ecommerceapp.model.cart.TotalCost
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import java.util.*
import javax.inject.Inject

class CartRepository @Inject constructor(private val cartDao: CartDao){
    suspend fun addCartProduct(cartProduct: CartProduct) = cartDao.addCartProduct(cartProduct = cartProduct)
    suspend fun updateCartProduct(cartProduct: CartProduct) = cartDao.updateCartProduct(cartProduct = cartProduct)
    suspend fun deleteCartProduct(cartProduct: CartProduct) = cartDao.deleteCartProduct(cartProduct = cartProduct)
    suspend fun deleteAllCartProduct() = cartDao.deleteAllCartProduct()
    fun getSingleCartProduct(id: UUID): Flow<CartProduct> = cartDao.getCartProductById(id).flowOn(Dispatchers.IO).conflate()
    fun getAllCartProduct(): Flow<List<CartProduct>> = cartDao.getCartProductList().flowOn(Dispatchers.IO)
        .conflate()
    fun getTotalCost():Flow<TotalCost> = cartDao.getTotalCost().flowOn(Dispatchers.IO).conflate()
}