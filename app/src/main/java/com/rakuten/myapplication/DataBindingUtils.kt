package com.rakuten.myapplication

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object DataBindingUtils {

    @JvmStatic
    @BindingAdapter("loadSrc")
    fun downloadImage(imageView: ImageView, url: String?) {
        Glide.with(imageView)
            .load(url)
            .into(imageView)
    }
}