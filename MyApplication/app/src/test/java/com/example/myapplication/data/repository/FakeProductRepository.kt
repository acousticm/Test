package com.example.myapplication.data.repository

import com.example.myapplication.data.model.ProductListModel
import com.example.myapplication.data.model.ProductListModelItem
import com.example.myapplication.data.utils.Resource
import com.example.myapplication.domain.repository.ProductRepository

class FakeProductRepository : ProductRepository {

    private val productListResObj = ProductListModel()
    private val productItemResObj = ProductListModelItem()

    init {
        productListResObj.add(
            ProductListModelItem(
                "TestContent",
                0,
                "test.url",
                true,
                123.03,
                "Test"
            )
        )

        productItemResObj.apply {
            id = 1150
            title = "ProductItem01"
            content = "ProductContent01"
            isNewProduct = false
            price = 115.22
            image = "google.com/image.jpg"
        }
    }

    override suspend fun getProductList(): Resource<ProductListModel> {
        return Resource.ApiSuccess(productListResObj)
    }

    override suspend fun getProductItem(productId: Int): Resource<ProductListModelItem> {
        return Resource.ApiSuccess(productItemResObj)
    }
}