package com.example.shopperapp.ui.wrapper



sealed class UIEvents{
    data object Loading : UIEvents()
    data class Success<T>(val data: T) : UIEvents()
    data class Error(val message: String): UIEvents()
}