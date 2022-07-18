package com.ecommerceapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ecommerceapp.dao.AddressDao
import com.ecommerceapp.dao.CartDao
import com.ecommerceapp.dao.ProductDao
import com.ecommerceapp.dao.ProfileDao
import com.ecommerceapp.model.address.Address
import com.ecommerceapp.model.cart.CartProduct
import com.ecommerceapp.model.products.HomeProduct
import com.ecommerceapp.model.profile.Profile
import com.ecommerceapp.utils.DateConverter
import com.ecommerceapp.utils.ListToStringConverter
import com.ecommerceapp.utils.UUIDConverter

@Database(entities = [Address::class, CartProduct::class, HomeProduct::class, Profile::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class, ListToStringConverter::class)
abstract class ECommerceDatabase : RoomDatabase() {
    abstract fun addressDao(): AddressDao
    abstract fun cartDao(): CartDao
    abstract fun productDao(): ProductDao
    abstract fun profileDao():ProfileDao
}