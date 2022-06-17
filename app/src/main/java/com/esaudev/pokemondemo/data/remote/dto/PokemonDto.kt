package com.esaudev.pokemondemo.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PokemonDto(
    val name: String,
    @SerializedName("url") var image: String
)
