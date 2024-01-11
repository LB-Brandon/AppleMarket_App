package com.brandon.applemarket_app

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import java.text.DecimalFormat

@Parcelize
data class ProductItem(
    val id: Int,
    var fileName: String,
    var productName: String,
    var productInfo: String,
    var seller: String,
    var price: Int,
    var address: String,
    var likeCount: Int,
    var chatCount: Int,
    @DrawableRes val imageResId: Int,
    var isLike: Boolean = false
): Parcelable

fun Int.formatPriceInWon(): String {
    return DecimalFormat("#,###").format(this) + "원"
}
