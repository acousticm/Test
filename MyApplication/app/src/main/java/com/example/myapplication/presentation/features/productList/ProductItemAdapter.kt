package com.example.myapplication.presentation.features.productList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.model.ProductListModelItem
import com.example.myapplication.databinding.VhProductItemBinding
import com.example.myapplication.presentation.extensions.loadImageUrl
import com.example.myapplication.presentation.extensions.toDecimal2Digits

class ProductItemAdapter : RecyclerView.Adapter<ProductItemAdapter.ProductItemViewHolder>() {

    var data: MutableList<ProductListModelItem> = ArrayList()

    var listener: ProductListener? = null

    interface ProductListener {
        fun onClickProductItem(
            item: ProductListModelItem,
            badgeView: TextView,
            tvTitle: TextView,
            imageView: ImageView,
            tvPrice: TextView
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProductItemViewHolder(inflater.inflate(R.layout.vh_product_item, parent, false))
    }

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class ProductItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: VhProductItemBinding = DataBindingUtil.bind(itemView)!!

        fun bind(item: ProductListModelItem) {
            binding.apply {
                tvTitle.text = item.title
                tvPrice.text = item.price.toDecimal2Digits()
                ivProduct.loadImageUrl(item.image)


                if (item.isNewProduct) {
                    tvBadgeNew.visibility = View.VISIBLE
                } else {
                    tvBadgeNew.visibility = View.GONE
                }

                root.setOnClickListener {
                    listener?.let {
                        tvBadgeNew.transitionName = "new"
                        tvTitle.transitionName = "title"
                        ivProduct.transitionName = "image"
                        tvPrice.transitionName = "price"
                        it.onClickProductItem(item, tvBadgeNew, tvTitle, ivProduct, tvPrice)
                    }
                }
            }
        }
    }
}