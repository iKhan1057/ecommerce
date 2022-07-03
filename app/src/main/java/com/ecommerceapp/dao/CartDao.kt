package com.ecommerceapp.dao

import androidx.room.*
import com.ecommerceapp.model.cart.CartProduct
import com.ecommerceapp.model.cart.TotalCost
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface CartDao {
    @Query("select * from tbl_cart")
    fun getCartProductList(): Flow<List<CartProduct>>

    @Query("select * from tbl_cart where product_id =:id")
    fun getCartProductById(id: UUID): Flow<CartProduct>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCartProduct(cartProduct: CartProduct)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCartProduct(cartProduct: CartProduct)

    @Delete
    suspend fun deleteCartProduct(cartProduct: CartProduct)

    @Query("Delete from tbl_cart")
    suspend fun deleteAllCartProduct()

    @Query("SELECT SUM(total_cost) as totalprice FROM tbl_cart")
    fun getTotalCost():Flow<TotalCost>
}