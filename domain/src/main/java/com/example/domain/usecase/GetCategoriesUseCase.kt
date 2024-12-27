package com.example.domain.usecase

import com.example.domain.repository.CategoriesRepository

class GetCategoriesUseCase(private val repository: CategoriesRepository) {
    suspend fun execute() = repository.getCategories()
}