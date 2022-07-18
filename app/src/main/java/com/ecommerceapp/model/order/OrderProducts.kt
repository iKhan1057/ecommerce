package com.ecommerceapp.model.order

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "tbl_order_products")
data class OrderProducts(
    @ColumnInfo(name = "order_id")
    val order_id: String = "",
    @ColumnInfo(name = "product_name")
    val product_name: String = "",
    @ColumnInfo(name = "product_id")
    val product_id: String = "",
    @ColumnInfo(name = "product_id")
    val product_photo: String = "",
    @ColumnInfo(name = "product_qty")
    val product_qty: String = "",
)