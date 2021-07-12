package com.example.myapplication.presentation.features.productItem

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.data.model.ProductListModelItem
import com.example.myapplication.data.utils.Resource
import com.example.myapplication.databinding.ActivityProductItemBinding
import com.example.myapplication.presentation.extensions.loadImageUrl
import com.example.myapplication.presentation.extensions.toDecimal2Digits
import com.example.myapplication.presentation.utils.ProgressUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProductItemActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: ProductItemViewModelFactory

    private lateinit var viewModel: ProductItemViewModel

    private lateinit var binding: ActivityProductItemBinding

    private lateinit var dataItem: ProductListModelItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_item)
        viewModel = ViewModelProvider(this, factory).get(ProductItemViewModel::class.java)
        dataItem = intent?.extras?.getParcelable("dataItem")!!
        setUpToolbar()
        viewModel.fetchProductItem(dataItem.id)
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.productItem.observe(this, {
            it?.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is Resource.ApiSuccess -> {
                        ProgressUtil.hideLoading()
                        setUpView(response.data)
                    }
                    is Resource.ApiSuccessError -> {
                        ProgressUtil.hideLoading()
                    }
                    is Resource.Error -> {
                        ProgressUtil.hideLoading()
                    }
                    is Resource.Loading -> {
                        ProgressUtil.showLoading(this)
                    }
                }
            }
        })
    }

    private fun setUpView(data: ProductListModelItem?) {
        binding.apply {
            data?.let {
                if (it.isNewProduct) {
                    tvBadgeNew.visibility = View.VISIBLE
                } else {
                    tvBadgeNew.visibility = View.GONE
                }
                tvTitle.text = it.title
                tvPrice.text = it.price.toDecimal2Digits()
                tvContent.text = it.content
                ivProduct.loadImageUrl(it.image)
            }
        }
    }

    private fun setUpToolbar() {
        binding.apply {
            appbar.tvTitle.text = getString(R.string.label_detail)
            appbar.ivBack.visibility = View.VISIBLE
            appbar.ivBack.setOnClickListener {
                supportFinishAfterTransition()
            }
        }
    }
}