package com.example.myapplication.domain.usecase

import com.example.myapplication.data.model.ProductListModelItem
import com.example.myapplication.data.utils.Resource
import com.example.myapplication.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductItemUseCase @Inject constructor(private val productRepository: ProductRepository) {
    suspend fun execute(productId: Int): Resource<ProductListModelItem> {
        return productRepository.getProductItem(productId)
    }
}