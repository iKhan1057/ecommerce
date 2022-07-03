package com.ecommerceapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ecommerceapp.dao.AddressDao
import com.ecommerceapp.dao.CartDao
import com.ecommerceapp.dao.ProductDao
import com.ecommerceapp.dao.ProfileDao
import com.ecommerceapp.db.ECommerceDatabase
import com.ecommerceapp.network.EcommerceApi
import com.ecommerceapp.utils.AppUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun providesProductApi() = Retrofit.Builder().baseUrl(AppUtils.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(EcommerceApi::class.java)

    @Singleton
    @Provides
    fun providesAddressDao(eCommerceDatabase: ECommerceDatabase): AddressDao =
        eCommerceDatabase.addressDao()

    @Singleton
    @Provides
    fun providesCartDao(eCommerceDatabase: ECommerceDatabase): CartDao = eCommerceDatabase.cartDao()

    @Singleton
    @Provides
    fun providesHomeDao(eCommerceDatabase: ECommerceDatabase):ProductDao = eCommerceDatabase.productDao()

    @Singleton
    @Provides
    fun providesProfileDao(eCommerceDatabase: ECommerceDatabase) :ProfileDao = eCommerceDatabase.profileDao()

    @Singleton
    @Provides
    fun providesAppDatabase(
        @ApplicationContext
        context: Context
    ) = Room.databaseBuilder(context, ECommerceDatabase::class.java, "ecommerce_database")
        .fallbackToDestructiveMigration().build()


}