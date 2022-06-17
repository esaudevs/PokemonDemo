package com.esaudev.pokemondemo.domain.repository

import androidx.paging.PagingData
import com.esaudev.pokemondemo.domain.model.Pokemon
import com.esaudev.pokemondemo.domain.model.PokemonDetail
import com.esaudev.pokemondemo.util.Resource
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    fun fetchPokemonWithPaging(): Flow<PagingData<Pokemon>>

    suspend fun fetchPokemonDetailById(pokemonId: Int): Resource<PokemonDetail>

}