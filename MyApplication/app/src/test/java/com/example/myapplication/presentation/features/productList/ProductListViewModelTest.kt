package com.example.myapplication.presentation.features.productList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myapplication.data.model.ProductListModel
import com.example.myapplication.data.model.ProductListModelItem
import com.example.myapplication.data.repository.FakeProductRepository
import com.example.myapplication.domain.usecase.GetProductListUseCase
import com.example.myapplication.presentation.utils.Event
import com.example.myapplication.rule.MainCoroutineRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class ProductListViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var viewModel: ProductListViewModel

    @Before
    fun setUp() {
        val fakeProductRepository = FakeProductRepository()
        val getProductListUseCase = GetProductListUseCase(fakeProductRepository)
        viewModel = ProductListViewModel(getProductListUseCase)
    }

    @Test
    fun getProductList_receivedExpected() = runBlocking {
        val mockUpResult = ProductListModel()
        mockUpResult.apply {
            add(
                ProductListModelItem(
                    "TestContent",
                    0,
                    "test.url",
                    true,
                    123.03,
                    "Test"
                )
            )
        }
        viewModel.fetchProductList()
        viewModel.productList.observeForever {
            it.getContentIfNotHandled()?.data.let { response ->
                assertThat(response).isEqualTo(mockUpResult)
                assertThat(response?.get(0)?.id).isEqualTo(0)
                assertThat(response?.get(0)?.title).isEqualTo("Test")
                assertThat(response?.get(0)?.content).isEqualTo("TestContent")
                assertThat(response?.get(0)?.image).isEqualTo("test.url")
                assertThat(response?.get(0)?.isNewProduct).isEqualTo(true)
                assertThat(response?.get(0)?.price).isEqualTo(123.03)
            }
        }
    }
}