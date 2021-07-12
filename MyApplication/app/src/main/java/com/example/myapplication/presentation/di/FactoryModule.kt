package com.example.myapplication.presentation.di

import com.example.myapplication.domain.usecase.GetProductItemUseCase
import com.example.myapplication.domain.usecase.GetProductListUseCase
import com.example.myapplication.presentation.features.productItem.ProductItemViewModel
import com.example.myapplication.presentation.features.productItem.ProductItemViewModelFactory
import com.example.myapplication.presentation.features.productList.ProductListViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {
    @Singleton
    @Provides
    fun provideProductListViewModelFactory(
        getProductListUseCase: GetProductListUseCase
    ): ProductListViewModelFactory {
        return ProductListViewModelFactory(getProductListUseCase)
    }

    @Singleton
    @Provides
    fun provideProductItemViewModelFactory(getProductItemUseCase: GetProductItemUseCase): ProductItemViewModelFactory {
        return ProductItemViewModelFactory(getProductItemUseCase)
    }
}