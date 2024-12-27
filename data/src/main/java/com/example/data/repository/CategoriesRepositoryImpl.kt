package com.example.data.repository

import com.example.domain.network.NetworkService
import com.example.domain.network.ResultWrapper
import com.example.domain.repository.CategoriesRepository

class CategoriesRepositoryImpl(private val networkService: NetworkService): CategoriesRepository  {
    override suspend fun getCategories(): ResultWrapper<List<String>> {
        return networkService.getCategories()
    }

}