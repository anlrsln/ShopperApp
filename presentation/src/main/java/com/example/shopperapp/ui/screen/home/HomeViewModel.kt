package com.example.presentation.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Product
import com.example.domain.network.ResultWrapper
import com.example.domain.usecase.GetProductUseCase
import com.example.shopperapp.ui.screen.home.HomeScreenUIEvents
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class HomeViewModel(private val getProductUseCase: GetProductUseCase) : ViewModel() {

    private val _productState = MutableStateFlow<HomeScreenUIEvents>(HomeScreenUIEvents.Loading)
    val productState = _productState.asStateFlow()

    init {
        getAllProducts()
    }

    private fun getAllProducts(){
        viewModelScope.launch{
            _productState.value = HomeScreenUIEvents.Loading
            val featured = getProducts("jewelery")
            val popularProducts = getProducts(null)
            if (featured.isEmpty() || popularProducts.isEmpty()){
                _productState.value = HomeScreenUIEvents.Error("Failed to load products.")
                return@launch
            }
            _productState.value = HomeScreenUIEvents.Success(featured,popularProducts)
        }
    }


    private suspend fun getProducts(category: String?):List<Product>{
            _productState.value = HomeScreenUIEvents.Loading
            getProductUseCase.execute(category).let { result ->
                when(result){
                    is ResultWrapper.Success -> {
                        return result.value
                    }
                    is ResultWrapper.Failure -> {
                        return emptyList()
                    }
                }
            }
    }
}


