package com.example.myapplication.data.api

import com.example.myapplication.data.model.ProductListModel
import com.example.myapplication.data.model.ProductListModelItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EcommerceApiService {
    @GET("/products")
    suspend fun getProductList(): Response<ProductListModel>

    @GET("/products/{Id}")
    suspend fun getProductItem(@Path("Id") id: Int): Response<ProductListModelItem>
}