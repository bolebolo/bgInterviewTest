package com.betsson.interviewtest.utils

import android.widget.ImageView
import com.betsson.interviewtest.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

//Extension for loading an image from the internet
fun ImageView.loadImage(url: String?) {
    Glide.with(this.context)
        .load(url)
        .placeholder(R.drawable.ic_placeholder)
        .error(R.drawable.ic_error_image)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}