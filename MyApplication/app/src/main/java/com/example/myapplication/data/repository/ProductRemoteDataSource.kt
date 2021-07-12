package com.example.myapplication.data.repository

import com.example.myapplication.data.model.ProductListModel
import com.example.myapplication.data.model.ProductListModelItem
import com.example.myapplication.data.utils.Resource

interface ProductRemoteDataSource {
    suspend fun getProductList(): Resource<ProductListModel>
    suspend fun getProductItem(productId: Int): Resource<ProductListModelItem>
}