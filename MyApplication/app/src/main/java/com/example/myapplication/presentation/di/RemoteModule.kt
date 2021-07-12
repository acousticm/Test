package com.example.myapplication.presentation.di

import com.example.myapplication.data.api.EcommerceApiService
import com.example.myapplication.data.repository.ProductRemoteDataSource
import com.example.myapplication.data.repository.ProductRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Singleton
    @Provides
    fun provideProductRemoteDataSource(ecommerceApiService: EcommerceApiService): ProductRemoteDataSource {
        return ProductRemoteDataSourceImpl(ecommerceApiService)
    }
}