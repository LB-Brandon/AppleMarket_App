package com.brandon.applemarket_app

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.brandon.applemarket_app.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar

class DetailActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(
            layoutInflater
        )
    }
    private val productItem: ProductItem? by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_PRODUCT_ITEM, ProductItem::class.java)
        } else {
            intent.getParcelableExtra(EXTRA_PRODUCT_ITEM)
        }
    }

    companion object {
        private const val EXTRA_PRODUCT_ITEM = "extra_product_item"

        fun moveToDetail(context: Context, productItem: ProductItem) {
            Intent(context, DetailActivity::class.java).apply {
                // 과제 요구 사항
                putExtra(EXTRA_PRODUCT_ITEM, productItem)
            }.also {
                context.startActivity(it)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()
        Log.d("Detail", "$productItem")
    }

    private fun initViews() {
        val position = MainActivity.dummyList.indexOf(productItem)
        Log.d("detail", "${productItem}")
        with(binding) {
            productItem?.imageResId?.let { ivProduct.setImageResource(it) }
            tvUserName.text = productItem?.seller
            tvProductLocation.text = productItem?.address
            tvProductName.text = productItem?.productName
            tvProductInfo.text = productItem?.productInfo
            tvProductPrice.text = productItem?.price?.formatPriceInWon()
            ivLike.isSelected = MainActivity.dummyList[position].isLike

            ivBackBtn.setOnClickListener { finish() }
            ivLike.setOnClickListener {
                // 아래 로직 개선 여지가 있을 것 같음. MVC 패턴의 한계인가..
                val currentState = MainActivity.dummyList[position].isLike
                // Product like state toggle
                MainActivity.dummyList[position].isLike = currentState.not()
                it.isSelected = MainActivity.dummyList[position].isLike
                if (MainActivity.dummyList[position].isLike) {
                    MainActivity.dummyList[position].likeCount++
                    Snackbar.make(ivLike, "관심 목록에 추가되었습니다", Snackbar.LENGTH_SHORT).show()
                } else {
                    MainActivity.dummyList[position].likeCount--
                }
            }
        }
    }

}