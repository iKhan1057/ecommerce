package com.ecommerceapp.model.address

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "tbl_address")
data class Address(
    @PrimaryKey
    @ColumnInfo(name = "address_id")
    var _id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "address_name")//can be home,office,other
    var addressname: String = "",
    @ColumnInfo(name = "address_one")
    var address1: String = "",
    @ColumnInfo(name = "address_two")
    var address2: String = "",
    @ColumnInfo(name = "pincode")
    var pincode: String = "",
    @ColumnInfo(name = "city")
    var city: String = "",
    @ColumnInfo(name = "state")
    var state: String = "",
    @ColumnInfo(name = "country")
    var country: String = "",
    @ColumnInfo(name = "username")
    var username: String = "",
    @ColumnInfo(name = "userid")
    var userid: String = "",
    @ColumnInfo(name = "mobile")
    var mobile: String = "",
    @ColumnInfo(name = "address_default")
    var address_default: Boolean = false
)
