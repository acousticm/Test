package com.example.myapplication.presentation.features.productItem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.domain.usecase.GetProductItemUseCase

class ProductItemViewModelFactory(private val getProductItemUseCase: GetProductItemUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductItemViewModel(getProductItemUseCase) as T
    }
}