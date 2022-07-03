package com.ecommerceapp.model


data class Product(
    var id: String,
    var category: String,
    var name: String,
    var photo: String,
    var price: String,
    var unit: String,
    var qty: Int = 0
)