package com.example.domain.di

import com.example.domain.usecase.GetProductUseCase
import org.koin.dsl.module

val domainModule = module {
    includes(useCaseModule)
}