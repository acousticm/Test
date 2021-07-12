package com.example.myapplication.presentation.di

import com.example.myapplication.data.repository.ProductRemoteDataSource
import com.example.myapplication.data.repository.ProductRepositoryImpl
import com.example.myapplication.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideProductRepository(productRemoteDataSource: ProductRemoteDataSource): ProductRepository {
        return ProductRepositoryImpl(productRemoteDataSource)
    }
}