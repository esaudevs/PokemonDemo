package com.esaudev.pokemondemo.presentation.detail

import androidx.lifecycle.*
import com.esaudev.pokemondemo.domain.model.PokemonDetail
import com.esaudev.pokemondemo.domain.usecase.GetPokemonDetailByIdUseCase
import com.esaudev.pokemondemo.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getPokemonDetailByIdUseCase: GetPokemonDetailByIdUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _pokemonDetail : MutableLiveData<Resource<PokemonDetail>> = MutableLiveData()
    val pokemonDetail : LiveData<Resource<PokemonDetail>>
        get() = _pokemonDetail

    init {
        val pokemonId = savedStateHandle.get<Int>("pokemonId")
        pokemonId?.let { getPokemonDetailById(pokemonId = it) }
    }

    private fun getPokemonDetailById(pokemonId: Int) {
        viewModelScope.launch {
            getPokemonDetailByIdUseCase(pokemonId = pokemonId).onEach { state ->
                _pokemonDetail.value = state
            }.launchIn(viewModelScope)
        }
    }

}