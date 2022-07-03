package com.ecommerceapp.model.products

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_products")
data class HomeProduct(
    @PrimaryKey
    @ColumnInfo(name = "product_id")
    val id: String,
    @ColumnInfo(name = "product_category")
    var category: String,
    @ColumnInfo(name = "product_name")
    var name: String,
    @ColumnInfo(name = "product_photo")
    var photo: String,
    @ColumnInfo(name = "product_price")
    var price: String,
    @ColumnInfo(name = "product_unit")
    var unit: String,
    @ColumnInfo(name = "product_quantity")
    var product_quantity:Int = 0
)