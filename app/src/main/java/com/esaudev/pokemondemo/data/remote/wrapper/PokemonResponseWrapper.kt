package com.esaudev.pokemondemo.data.remote.wrapper

data class PokemonResponseWrapper<out T>(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: T
)
