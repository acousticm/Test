package com.example.myapplication.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
class ProductListModel : ArrayList<ProductListModelItem>(), Parcelable