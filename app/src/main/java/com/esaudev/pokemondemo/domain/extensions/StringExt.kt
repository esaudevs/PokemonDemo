package com.esaudev.pokemondemo.domain.extensions

import java.util.*

/**
 * Caps the sentence eg. charizard -> Charizard
 */
fun String.capSentence(): String {
    return this.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.getDefault()
        ) else it.toString()
    }
}