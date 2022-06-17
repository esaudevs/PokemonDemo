package com.esaudev.pokemondemo.data.remote.mappers

import com.esaudev.pokemondemo.data.remote.dto.PokemonDetailDto
import com.esaudev.pokemondemo.data.remote.dto.StatDto
import com.esaudev.pokemondemo.domain.model.PokemonDetail
import com.esaudev.pokemondemo.domain.model.Stat

fun PokemonDetailDto.mapToDomain(pokemonId: Int): PokemonDetail {
    return PokemonDetail(
        id = pokemonId,
        name = name,
        abilities = abilities.map {
            it.ability.name
        },
        image = sprites.frontDefault.orEmpty(),
        stats = stats.map {
            it.mapToDomain()
        },
        types = types.map {
            it.type.name
        },
        weight = weight,
        height = height
    )
}

fun StatDto.mapToDomain(): Stat {
    return Stat(
        name = stat.name,
        value = baseStat
    )
}