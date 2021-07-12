package com.example.myapplication.data.repository

import com.example.myapplication.data.model.ProductListModel
import com.example.myapplication.data.model.ProductListModelItem
import com.example.myapplication.data.utils.Resource
import com.example.myapplication.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val productRemoteDataSource: ProductRemoteDataSource) :
    ProductRepository {

    override suspend fun getProductList(): Resource<ProductListModel> {
        return try {
            productRemoteDataSource.getProductList()
        } catch (e: Exception) {
            Resource.Error("No Internet Connection")
        }
    }

    override suspend fun getProductItem(productId: Int): Resource<ProductListModelItem> {
        return try {
            productRemoteDataSource.getProductItem(productId)
        } catch (e: Exception) {
            Resource.Error("No Internet Connection")
        }
    }
}