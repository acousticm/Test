package com.example.myapplication.presentation.features.productList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.domain.usecase.GetProductListUseCase

class ProductListViewModelFactory(private val getProductListUseCase: GetProductListUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductListViewModel(getProductListUseCase) as T
    }
}