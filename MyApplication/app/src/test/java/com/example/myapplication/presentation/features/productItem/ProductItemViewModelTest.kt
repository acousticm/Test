package com.example.myapplication.presentation.features.productItem

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myapplication.data.model.ProductListModel
import com.example.myapplication.data.model.ProductListModelItem
import com.example.myapplication.data.repository.FakeProductRepository
import com.example.myapplication.domain.usecase.GetProductItemUseCase
import com.example.myapplication.domain.usecase.GetProductListUseCase
import com.example.myapplication.presentation.features.productList.ProductListViewModel
import com.example.myapplication.rule.MainCoroutineRule
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class ProductItemViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var viewModel: ProductItemViewModel

    @Before
    fun setUp() {
        val fakeProductRepository = FakeProductRepository()
        val getProductItemUseCase = GetProductItemUseCase(fakeProductRepository)
        viewModel = ProductItemViewModel(getProductItemUseCase)
    }

    @Test
    fun getProductItemById_receivedExpected() = runBlocking {
        viewModel.fetchProductItem(20)
        viewModel.productItem.observeForever {
            it.getContentIfNotHandled()?.data.let { response ->
                Truth.assertThat(response?.id).isEqualTo(1150)
                Truth.assertThat(response?.title).isEqualTo("ProductItem01")
                Truth.assertThat(response?.content).isEqualTo("ProductContent01")
                Truth.assertThat(response?.image).isEqualTo("google.com/image.jpg")
                Truth.assertThat(response?.isNewProduct).isEqualTo(false)
                Truth.assertThat(response?.price).isEqualTo(115.22)
            }
        }
    }
}