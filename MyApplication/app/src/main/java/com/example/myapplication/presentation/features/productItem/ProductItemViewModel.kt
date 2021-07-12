package com.example.myapplication.presentation.features.productItem

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.ProductListModel
import com.example.myapplication.data.model.ProductListModelItem
import com.example.myapplication.data.utils.Resource
import com.example.myapplication.domain.usecase.GetProductItemUseCase
import com.example.myapplication.presentation.utils.Event
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductItemViewModel @Inject constructor(private val getProductItemUseCase: GetProductItemUseCase) :
    ViewModel() {

    val productItem: MutableLiveData<Event<Resource<ProductListModelItem>>> = MutableLiveData()

    fun fetchProductItem(productId: Int) = viewModelScope.launch {
        productItem.postValue(Event(Resource.Loading()))
        productItem.postValue(Event(getProductItemUseCase.execute(productId)))
    }
}