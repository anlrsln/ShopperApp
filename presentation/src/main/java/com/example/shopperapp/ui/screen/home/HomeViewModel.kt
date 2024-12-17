package com.example.presentation.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Product
import com.example.domain.network.ResultWrapper
import com.example.domain.usecase.GetProductUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class HomeViewModel(private val getProductUseCase: GetProductUseCase) : ViewModel() {

    private val _productState = MutableStateFlow<HomeScreenUIEvents>(HomeScreenUIEvents.Loading)
    val productState = _productState.asStateFlow()
    init {
        getProducts()
    }
    fun getProducts(){
        viewModelScope.launch {
            _productState.value = HomeScreenUIEvents.Loading
            getProductUseCase.execute().let { result ->
                when(result){
                    is ResultWrapper.Success -> {
                        val data = result.value
                        _productState.value = HomeScreenUIEvents.Success(data)
                    }
                    is ResultWrapper.Failure -> {
                        val error = result.exception.message ?: "An Error Occured."
                        HomeScreenUIEvents.Error(error)
                    }
                }
            }
        }
    }
}


sealed class HomeScreenUIEvents{
    data object Loading : HomeScreenUIEvents()
    data class Success(val data : List<Product>):HomeScreenUIEvents()
    data class Error(val message: String):HomeScreenUIEvents()
}