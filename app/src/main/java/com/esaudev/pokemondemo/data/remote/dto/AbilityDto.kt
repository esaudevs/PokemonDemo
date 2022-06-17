package com.esaudev.pokemondemo.data.remote.dto

import com.esaudev.pokemondemo.data.remote.dto.AbilityDetailDto
import com.google.gson.annotations.SerializedName

data class AbilityDto (
    val ability: AbilityDetailDto,
    @SerializedName("is_hidden") val isHidden: Boolean,
    val slot: Int
        )