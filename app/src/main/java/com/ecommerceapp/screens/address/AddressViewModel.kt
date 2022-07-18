package com.ecommerceapp.screens.address

import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecommerceapp.model.address.Address
import com.ecommerceapp.repository.AddressRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(private val addressRepository: AddressRepository) :
    ViewModel() {
    private val _addressList = MutableStateFlow<List<Address>>(emptyList())
    var addresslist = _addressList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            addressRepository.getAllAddress().distinctUntilChanged().collect { addressList ->
                _addressList.value = addressList
            }
        }
    }

    fun addAddress(address: Address) =
        viewModelScope.launch { addressRepository.addAddress(address = address) }

    fun updateAddress(address: Address) =
        viewModelScope.launch { addressRepository.updateAddress(address = address) }

    fun deleteAddress(address: Address) =
        viewModelScope.launch { addressRepository.deleteAddress(address = address) }

    fun deleteAllAddress() = viewModelScope.launch { addressRepository.deleteAllAddress() }

    fun getSingleAddress(id: UUID): MutableStateFlow<Address> {
        val _address = MutableStateFlow(Address())
        viewModelScope.launch(Dispatchers.IO) {
            addressRepository.getSingleAddress(id = id).distinctUntilChanged().collect {
                _address.value = it
            }
        }
        return _address
    }

    fun getAddressByName(): MutableStateFlow<List<Address>> {
        val _address = MutableStateFlow<List<Address>>(emptyList())
        viewModelScope.launch(Dispatchers.IO) {
            addressRepository.getAddressByName().distinctUntilChanged().collect {
                _address.value = it
            }
        }
        return _address
    }

    fun getAddressByDefault(default: Boolean): MutableStateFlow<Address> {
        val _address = MutableStateFlow(Address())
        viewModelScope.launch(Dispatchers.IO) {
            addressRepository.getAddressByDefault(default = default).distinctUntilChanged()
                .collect {
                    _address.value = it
                }
        }
        return _address
    }

}