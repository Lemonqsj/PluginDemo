package com.lemon.lib_base.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.lemon.lib_base.R

fun ImageView.loadImage(url: String) {
    Glide.with(this).load(url).apply(
        RequestOptions().diskCacheStrategy(
            DiskCacheStrategy.RESOURCE
        ).skipMemoryCache(true).dontAnimate().placeholder(R.drawable.ic_placeholder)
    ).thumbnail(0.6f)
        .into(this)
}