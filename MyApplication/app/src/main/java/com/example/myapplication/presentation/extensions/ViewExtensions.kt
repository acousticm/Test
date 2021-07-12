package com.example.myapplication.presentation.extensions

import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.myapplication.R

fun ImageView.loadImageUrl(url: String) {
    Glide.with(this)
        .load(url)
        .placeholder(ContextCompat.getDrawable(this.context, R.drawable.ic_placeholder))
        .fitCenter()
        .into(this)
}

fun Double.toDecimal2Digits(): String {
    return (Math.round(this * 100.0) / 100.0).toString()
}