package com.esaudev.pokemondemo.domain.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Loads and URL image to its imageview
 */
fun ImageView.load(url: String) {
    Glide.with(context)
        .load(url)
        .centerCrop()
        .into(this)
}