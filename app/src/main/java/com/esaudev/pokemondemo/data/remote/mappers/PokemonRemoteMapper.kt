package com.esaudev.pokemondemo.data.remote.mappers

import com.esaudev.pokemondemo.data.remote.dto.PokemonDto
import com.esaudev.pokemondemo.domain.model.Pokemon

fun PokemonDto.mapToDomain(): Pokemon {
    return Pokemon(
        id = getPokemonId(image),
        name = name,
        image = getPokemonImage(image)
    )
}

private fun getPokemonId(image: String): Int {
    val pokemonId = image.replace("/","").removePrefix("https:pokeapi.coapiv2pokemon")
    return pokemonId.toInt()
}

private fun getPokemonImage(image: String): String {
    val pokemonId = image.replace("/", "").removePrefix("https:pokeapi.coapiv2pokemon")
    return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$pokemonId.png"
}

fun List<PokemonDto>.mapToDomain(): List<Pokemon> {
    return this.map { it.mapToDomain() }
}