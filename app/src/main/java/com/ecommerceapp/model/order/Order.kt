package com.ecommerceapp.model.order

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "tbl_order")
data class Order(
    @PrimaryKey
    @ColumnInfo(name = "order_id")
    val order_id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "order_date")
    val order_date: String = "",
    @ColumnInfo(name = "order_status")
    val order_status: String = "",//"Ordered,Intransit,Completed"
    @ColumnInfo(name = "order_currency")
    val order_currency: String = "",
)
