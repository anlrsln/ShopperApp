package com.example.presentation.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Product
import com.example.domain.network.ResultWrapper
import com.example.domain.usecase.GetCategoriesUseCase
import com.example.domain.usecase.GetProductUseCase
import com.example.shopperapp.ui.wrapper.HomeScreenUIEvents
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class HomeViewModel(
    private val getProductUseCase: GetProductUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
    ) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeScreenUIEvents>(HomeScreenUIEvents.Loading)
    val uiState = _uiState.asStateFlow()


    init {
        getAllProducts()
    }

    private fun getAllProducts(){
        viewModelScope.launch{
            _uiState.value = HomeScreenUIEvents.Loading
            val featured = getProducts("jewelery")
            val popularProducts = getProducts(null)
            val categories = getCategories()
            if (featured.isEmpty() || popularProducts.isEmpty()){
                _uiState.value = HomeScreenUIEvents.Error("Failed to load products.")
                return@launch
            }
            _uiState.value = HomeScreenUIEvents.Success(featured,popularProducts,categories)
        }
    }


    private suspend fun getProducts(category: String?):List<Product>{
            _uiState.value = HomeScreenUIEvents.Loading
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


    private suspend fun getCategories(): List<String>{
        getCategoriesUseCase.execute().let{ result ->
            when(result){
                is ResultWrapper.Success -> {
                    return (result.value)
                }
                is ResultWrapper.Failure -> {
                    return emptyList()
                }
            }
        }
    }


}


