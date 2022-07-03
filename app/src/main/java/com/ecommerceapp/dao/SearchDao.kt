package com.ecommerceapp.dao

import androidx.room.Dao
import androidx.room.Query
import com.ecommerceapp.model.products.HomeProduct
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchDao {
    @Query("SELECT tbl_products. *, tbl_cart.product_quantity FROM tbl_products LEFT JOIN tbl_cart ON tbl_products.product_id = tbl_cart.product_id")
    fun getProductList(): Flow<List<HomeProduct>>
}