package com.example.myapplication.data.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductListModelItem(
    @SerializedName("content")
    var content: String = "",
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("image")
    var image: String = "",
    @SerializedName("isNewProduct")
    var isNewProduct: Boolean = false,
    @SerializedName("price")
    var price: Double = 0.0,
    @SerializedName("title")
    var title: String = ""
) : Parcelable