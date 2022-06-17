package com.esaudev.pokemondemo.domain.usecase

import androidx.paging.PagingData
import com.esaudev.pokemondemo.domain.model.Pokemon
import com.esaudev.pokemondemo.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonListWithPagingUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {

    operator fun invoke(): Flow<PagingData<Pokemon>> = pokemonRepository.fetchPokemonWithPaging()

}