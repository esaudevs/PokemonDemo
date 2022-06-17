package com.esaudev.pokemondemo.data.remote.dto

import com.esaudev.pokemondemo.data.remote.dto.StatDetailDto
import com.google.gson.annotations.SerializedName

data class StatDto(
    @SerializedName("base_stat") val baseStat: Int,
    val effort: Int,
    val stat: StatDetailDto
)
