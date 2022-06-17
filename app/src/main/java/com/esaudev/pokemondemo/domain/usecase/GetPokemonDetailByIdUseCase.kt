package com.esaudev.pokemondemo.domain.usecase

import com.esaudev.pokemondemo.domain.model.PokemonDetail
import com.esaudev.pokemondemo.domain.repository.PokemonRepository
import com.esaudev.pokemondemo.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPokemonDetailByIdUseCase @Inject constructor(
    private val repository: PokemonRepository
) {

    suspend operator fun invoke(pokemonId: Int): Flow<Resource<PokemonDetail>> = flow {

        emit(Resource.Loading)

        val pokemonDetailState = repository.fetchPokemonDetailById(pokemonId)

        emit(pokemonDetailState)
    }

}