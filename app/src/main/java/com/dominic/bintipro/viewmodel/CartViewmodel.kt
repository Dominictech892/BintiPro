package com.dominic.bintipro.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.dominic.bintipro.ui.screens.home.Product

class CartViewModel : ViewModel() {

    // Holds Products added to cart
    private val _cartItems = mutableStateListOf<Product>()
    val cartItems: List<Product> get() = _cartItems

    fun addToCart(product: Product) {
        _cartItems.add(product)
    }

    fun removeFromCart(product: Product) {
        _cartItems.remove(product)
    }

    fun getTotalPrice(): Double {
        return _cartItems.sumOf { product ->
            // Remove "Ksh" and convert to Double
            product.price.replace("Ksh", "").replace(",", "").toDouble()
        }
    }
}
