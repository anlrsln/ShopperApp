package com.example.domain.usecase

import com.example.domain.model.Product
import com.example.domain.network.ResultWrapper
import com.example.domain.repository.ProductRepository

class GetProductUseCase(private val repository: ProductRepository) {
    suspend fun execute(category:String?) = repository.getProduct(category)
}