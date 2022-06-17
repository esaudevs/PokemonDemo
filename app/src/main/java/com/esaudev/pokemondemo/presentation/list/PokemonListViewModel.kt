package com.esaudev.pokemondemo.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.esaudev.pokemondemo.domain.model.Pokemon
import com.esaudev.pokemondemo.domain.usecase.GetPokemonListWithPagingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemonListWithPagingUseCase: GetPokemonListWithPagingUseCase
): ViewModel() {

    val pokemonListPaging: Flow<PagingData<Pokemon>> = getPokemonListWithPagingUseCase().cachedIn(viewModelScope)

}