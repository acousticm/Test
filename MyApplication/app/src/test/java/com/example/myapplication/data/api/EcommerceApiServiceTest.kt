package com.example.myapplication.data.api

import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EcommerceApiServiceTest {
    private lateinit var service: EcommerceApiService
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(EcommerceApiService::class.java)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    private fun enqueueMockResponse(fileName: String) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }

    @Test
    fun getProductList_sentRequest_receivedExpected() {
        runBlocking {
            enqueueMockResponse("productList.json")
            val responseBody = service.getProductList()
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(responseBody.body()?.get(0)?.id).isEqualTo(1)
            assertThat(
                responseBody.body()?.get(0)?.title
            ).isEqualTo("Signature Chocolate Chip Lactation Cookies")
            assertThat(
                responseBody.body()?.get(0)?.image
            ).isEqualTo("https://firebasestorage.googleapis.com/v0/b/test-4c60c.appspot.com/o/cookie1%402x.png?alt=media&token=dadc7377-1b3b-439a-9948-8237ec944d07")
            assertThat(responseBody.body()?.get(0)?.isNewProduct).isTrue()
            assertThat(responseBody.body()?.get(0)?.price).isEqualTo(18.569)
            assertThat(request.path).isEqualTo("/products")
        }
    }

    @Test
    fun getProductItem_sendRequest_receivedExpected() {
        runBlocking {
            enqueueMockResponse("productItemByIdResponse.json")
            val productId = 2
            val responseBody = service.getProductItem(productId)
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(responseBody.body()?.id).isEqualTo(2)
            assertThat(
                responseBody.body()?.title
            ).isEqualTo("Signature Chocolate Chip Lactation Cookies")
            assertThat(
                responseBody.body()?.image
            ).isEqualTo("https://firebasestorage.googleapis.com/v0/b/test-4c60c.appspot.com/o/cookie1%402x.png?alt=media&token=dadc7377-1b3b-439a-9948-8237ec944d07")
            assertThat(responseBody.body()?.isNewProduct).isTrue()
            assertThat(responseBody.body()?.price).isEqualTo(18.563)
            assertThat(request.path).isEqualTo("/products/$productId")
        }
    }
}