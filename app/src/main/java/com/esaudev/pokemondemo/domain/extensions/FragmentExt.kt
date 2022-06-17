package com.esaudev.pokemondemo.domain.extensions

import androidx.fragment.app.Fragment

/**
 * Returns to the preview screen in the nav graph
 */
fun Fragment.onBackPressed() {
    requireActivity().onBackPressed()
}