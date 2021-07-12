package com.example.myapplication.presentation.di

import com.example.myapplication.domain.repository.ProductRepository
import com.example.myapplication.domain.usecase.GetProductListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetProductListUseCase(productRepository: ProductRepository): GetProductListUseCase {
        return GetProductListUseCase(productRepository)
    }
}