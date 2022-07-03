package com.ecommerceapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ecommerceapp.dao.AddressDao
import com.ecommerceapp.model.address.Address
import dagger.internal.SingleCheck
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import java.util.*
import javax.inject.Inject

class AddressRepository @Inject constructor(private val addressDao: AddressDao) {

    suspend fun addAddress(address: Address) = addressDao.addAddress(address = address)

    suspend fun updateAddress(address: Address) = addressDao.updateAddress(address = address)

    suspend fun deleteAddress(address: Address) = addressDao.deleteAddress(address = address)

    suspend fun deleteAllAddress() = addressDao.deleteAllAddress()

    fun getSingleAddress(id:UUID): Flow<Address> = addressDao.getAddressById(id)

    fun getAllAddress(): Flow<List<Address>> = addressDao.getAddressList().flowOn(Dispatchers.IO)
        .conflate()
}