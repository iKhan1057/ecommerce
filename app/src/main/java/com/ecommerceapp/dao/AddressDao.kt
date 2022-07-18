package com.ecommerceapp.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.ecommerceapp.model.address.Address
import com.ecommerceapp.model.cart.TotalCost
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface AddressDao {
    @Query("select * from tbl_address")
    fun getAddressList(): Flow<List<Address>>

    @Query("select * from tbl_address where address_id =:id")
    fun getAddressById(id: UUID): Flow<Address>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAddress(address: Address)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAddress(address: Address)

    @Delete
    suspend fun deleteAddress(address: Address)

    @Query("Delete from tbl_address")
    suspend fun deleteAllAddress()

    @Query("select * from tbl_address where address_name in ('Home','Office')")
    fun getAddressByName():Flow<List<Address>>

    @Query("select * from tbl_address where address_default =:defaultval")
    fun getAddressByDefaultState(defaultval: Boolean): Flow<Address>
}