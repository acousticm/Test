package com.example.myapplication.presentation.features.productList

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.data.model.ProductListModelItem
import com.example.myapplication.data.utils.Resource
import com.example.myapplication.databinding.ActivityProductListBinding
import com.example.myapplication.presentation.features.productItem.ProductItemActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import androidx.core.app.ActivityCompat
import androidx.core.util.Pair as UtilPair

import androidx.core.app.ActivityOptionsCompat
import com.example.myapplication.presentation.utils.ProgressUtil


@AndroidEntryPoint
class ProductListActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ProductListViewModelFactory
    private lateinit var viewModel: ProductListViewModel

    lateinit var mAdapter: ProductItemAdapter

    private lateinit var binding: ActivityProductListBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_list)
        viewModel = ViewModelProvider(this, factory).get(ProductListViewModel::class.java)
        viewModel.fetchProductList()
        setUpToolbar()
        setUpAdapter()
        observeLiveData()
    }

    private fun setUpToolbar() {
        binding.toolbar.tvTitle.text = getString(R.string.label_title_product)
    }

    private fun setUpAdapter() {
        mAdapter = ProductItemAdapter()
        binding.recyclerviewProduct.apply {
            val mLayoutManager =
                GridLayoutManager(this@ProductListActivity, 2, LinearLayoutManager.VERTICAL, false)
            layoutManager = mLayoutManager
            adapter = mAdapter
            mAdapter.listener = object : ProductItemAdapter.ProductListener {
                override fun onClickProductItem(
                    item: ProductListModelItem,
                    badgeView: TextView,
                    tvTitle: TextView,
                    imageView: ImageView,
                    tvPrice: TextView
                ) {
                    val intent = Intent(this@ProductListActivity, ProductItemActivity::class.java)
                    val options: ActivityOptionsCompat =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                            this@ProductListActivity,
                            UtilPair.create(badgeView, badgeView.transitionName),
                            UtilPair.create(tvTitle, tvTitle.transitionName),
                            UtilPair.create(imageView, imageView.transitionName),
                            UtilPair.create(tvPrice, tvPrice.transitionName)
                        )
                    intent.apply {
                        putExtra("dataItem", item)
                    }.also {
                        startActivity(it, options.toBundle())
                    }
                }

            }
        }
    }

    override fun onResume() {
        super.onResume()
        mAdapter.let {
            it.notifyDataSetChanged()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeLiveData() {
        viewModel.productList.observe(this, {
            it?.getContentIfNotHandled().let { response ->
                when (response) {
                    is Resource.ApiSuccess -> {
                        ProgressUtil.hideLoading()
                        mAdapter.data = response.data!!
                        mAdapter.notifyDataSetChanged()
                    }
                    is Resource.ApiSuccessError -> {
                        ProgressUtil.hideLoading()
                    }
                    is Resource.Error -> {
                        ProgressUtil.hideLoading()
                        Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Loading -> {
                        ProgressUtil.showLoading(this)
                    }
                }
            }
        })
    }
}