package com.esaudev.pokemondemo.domain.model

data class PokemonDetail(
    val id: Int,
    val name: String,
    val abilities: List<String>,
    val image: String,
    val stats: List<Stat>,
    val types: List<String>,
    val weight: Int,
    val height: Int
)
