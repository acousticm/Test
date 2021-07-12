package com.example.myapplication.data.repository

import com.example.myapplication.data.api.EcommerceApiService
import com.example.myapplication.data.model.ProductListModel
import com.example.myapplication.data.model.ProductListModelItem
import com.example.myapplication.data.utils.Resource
import javax.inject.Inject

class ProductRemoteDataSourceImpl @Inject constructor(private val ecommerceApiService: EcommerceApiService) :
    ProductRemoteDataSource {

    override suspend fun getProductList(): Resource<ProductListModel> {
        val response = ecommerceApiService.getProductList()
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.ApiSuccess(result)
            }
        }
        return Resource.Error("Exception")
    }

    override suspend fun getProductItem(productId: Int): Resource<ProductListModelItem> {
        val response = ecommerceApiService.getProductItem(productId)
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.ApiSuccess(result)
            }
        }
        return Resource.Error("Exception")
    }
}