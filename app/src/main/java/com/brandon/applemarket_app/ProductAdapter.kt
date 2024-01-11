package com.brandon.applemarket_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brandon.applemarket_app.databinding.ItemProductBinding

class ProductAdapter(private val productItemList: MutableList<ProductItem>) :
    RecyclerView.Adapter<ProductAdapter.ProductHolder>() {

    interface ItemClick {
        fun onClick(view: View, position: Int)
        fun onLongClick(view: View, position: Int)
    }

    var itemClick: ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        with(holder){
            bind(productItemList[position])
            itemView.setOnClickListener {
                itemClick?.onClick(it, position)
            }
            itemView.setOnLongClickListener{
                itemClick?.onLongClick(it, position)
                true// event 소비
            }
        }
    }
    override fun getItemCount(): Int = productItemList.size

    fun removeItem(position: Int){
        productItemList.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class ProductHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(productItem: ProductItem) {
            binding.tvProductName.text = productItem.productName
            binding.tvProductLocation.text = productItem.address
            binding.tvProductPrice.text = productItem.price.formatPriceInWon()
            binding.tvChatCount.text = productItem.chatCount.toString()
            binding.tvLikeCount.text = productItem.likeCount.toString()
            binding.ivProduct.setImageResource(productItem.imageResId)
            binding.ivLike.isSelected = productItem.isLike
        }
    }
}