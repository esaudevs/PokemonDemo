package com.esaudev.pokemondemo.domain.extensions

import android.content.Context

/**
 * Returns Dps from Pixels
 * @param px
 */
fun Context.pxToDp(px: Int): Int {
    return (px / resources.displayMetrics.density).toInt()
}

/**
 * Returns Pixels from Dps
 * @param dp
 */
fun Context.dpToPx(dp: Int): Int {
    return (dp * resources.displayMetrics.density).toInt()
}