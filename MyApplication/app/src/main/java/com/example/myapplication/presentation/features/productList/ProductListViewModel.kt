package com.example.myapplication.presentation.features.productList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.ProductListModel
import com.example.myapplication.data.utils.Resource
import com.example.myapplication.domain.usecase.GetProductListUseCase
import com.example.myapplication.presentation.utils.Event
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductListViewModel @Inject constructor(private val getProductListUseCase: GetProductListUseCase) :
    ViewModel() {

    val productList: MutableLiveData<Event<Resource<ProductListModel>>> = MutableLiveData()

    fun fetchProductList() = viewModelScope.launch {
        productList.postValue(Event(Resource.Loading()))
        productList.postValue(Event(getProductListUseCase.execute()))
    }
}