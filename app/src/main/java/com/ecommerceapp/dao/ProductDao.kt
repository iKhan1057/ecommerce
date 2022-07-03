package com.ecommerceapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.ecommerceapp.model.Product
import com.ecommerceapp.model.products.HomeProduct
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
//    @Query("select * from tbl_products")
//    fun getHomeProductList(): Flow<List<HomeProduct>>

    @Insert(onConflict = REPLACE)
    suspend fun insertAllProducts(products: ArrayList<HomeProduct>)

    @Query("SELECT tbl_products. *, tbl_cart.product_quantity FROM tbl_products LEFT JOIN tbl_cart ON tbl_products.product_id = tbl_cart.product_id")
    fun getHomeProductList(): Flow<List<HomeProduct>>

    @Update(onConflict = REPLACE)
    suspend fun updateProduct(product: HomeProduct)
}