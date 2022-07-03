package com.ecommerceapp.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecommerceapp.data.DataOrException
import com.ecommerceapp.model.ProductsList
import com.ecommerceapp.model.products.HomeProduct
import com.ecommerceapp.repository.ECommerceRepository
import com.ecommerceapp.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ECommerceRepository,
    private val productRepository: ProductRepository
) : ViewModel() {
    init {
        getProductListData()
    }

    private val _productList = MutableStateFlow<List<HomeProduct>>(emptyList())
    var productlist = _productList.asStateFlow()

    private fun getProductListData(): Flow<List<HomeProduct>> {
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.getAllProducts().distinctUntilChanged().collect {
                _productList.value = it
            }
        }
        return productlist
    }

    suspend fun getProductListDataFromServer(): DataOrException<ProductsList, Boolean, Exception> {
        return repository.getAllProducts()
    }

    fun insertAllDataToDb(product: ArrayList<HomeProduct>){
        viewModelScope.launch {
            productRepository.insertAllProducts(product)
        }
    }

    fun updateProduct(product: HomeProduct){
        viewModelScope.launch {
            productRepository.updateProduct(product)
        }
    }
}
