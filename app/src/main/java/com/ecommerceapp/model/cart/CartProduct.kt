package com.ecommerceapp.model.cart

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "tbl_cart")
data class CartProduct(
    @PrimaryKey
    @ColumnInfo(name = "product_id")
    val productid: String = "",
    @ColumnInfo(name = "product_category")
    val category: String = "",
    @ColumnInfo(name = "product_name")
    val name: String = "",
    @ColumnInfo(name = "product_photo")
    val photo: String = "",
    @ColumnInfo(name = "product_price")
    val price: String = "",
    @ColumnInfo(name = "product_unit")
    val unit: String = "",
    @ColumnInfo(name = "product_quantity")
    var qty: Int = 0,
    @ColumnInfo(name = "total_cost")
    var totalCost: Double = 0.0
)
