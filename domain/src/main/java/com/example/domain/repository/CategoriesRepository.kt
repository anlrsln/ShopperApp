package com.example.domain.repository

import com.example.domain.network.ResultWrapper

interface CategoriesRepository {
    suspend fun getCategories(): ResultWrapper<List<String>>
}