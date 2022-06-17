package com.esaudev.pokemondemo.data.remote.dto

data class PokemonDetailDto(
    val name: String,
    val abilities: List<AbilityDto>,
    val sprites: SpritesDto,
    val stats: List<StatDto>,
    val types: List<TypeDto>,
    val weight: Int,
    val height: Int
)
