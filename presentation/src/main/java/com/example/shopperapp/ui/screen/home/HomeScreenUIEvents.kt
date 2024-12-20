package com.example.shopperapp.ui.screen.home

import com.example.domain.model.Product

sealed class HomeScreenUIEvents{
    data object Loading : HomeScreenUIEvents()
    data class Success(val featured : List<Product>,val popularProducts : List<Product>):HomeScreenUIEvents()
    data class Error(val message: String):HomeScreenUIEvents()
}