package com.example.myapplication.domain.usecase

import com.example.myapplication.data.model.ProductListModel
import com.example.myapplication.data.utils.Resource
import com.example.myapplication.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductListUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {

    suspend fun execute(): Resource<ProductListModel> {
        return productRepository.getProductList()
    }
}