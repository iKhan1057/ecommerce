package com.ecommerceapp.screens.cart

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecommerceapp.model.cart.CartProduct
import com.ecommerceapp.model.cart.TotalCost
import com.ecommerceapp.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val cartRepository: CartRepository) : ViewModel() {
    private val _cartproduct = MutableStateFlow<List<CartProduct>>(emptyList())
    var cartProduct = _cartproduct.asStateFlow()



    init {
        viewModelScope.launch(Dispatchers.IO) {
            cartRepository.getAllCartProduct().distinctUntilChanged().collect {
                _cartproduct.value = it
            }
        }
    }

    fun addCartProduct(cartProduct: CartProduct) =
        viewModelScope.launch { cartRepository.addCartProduct(cartProduct = cartProduct) }

    fun updateCartProduct(cartProduct: CartProduct) =
        viewModelScope.launch { cartRepository.updateCartProduct(cartProduct = cartProduct) }

    fun deleteCartProduct(cartProduct: CartProduct) =
        viewModelScope.launch { cartRepository.deleteCartProduct(cartProduct = cartProduct) }

    fun deleteAllCartProduct() = viewModelScope.launch { cartRepository.deleteAllCartProduct() }
    fun getTotalCost(): MutableStateFlow<TotalCost> {
        val _cartProductTotalCost = MutableStateFlow(TotalCost())
        viewModelScope.launch(Dispatchers.IO) {
            cartRepository.getTotalCost().distinctUntilChanged().collect {
                _cartProductTotalCost.value = it
            }
        }
        return _cartProductTotalCost
    }
}