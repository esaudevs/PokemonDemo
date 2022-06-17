package com.esaudev.pokemondemo.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.esaudev.pokemondemo.data.remote.api.PokemonApi
import com.esaudev.pokemondemo.data.remote.mappers.mapToDomain
import com.esaudev.pokemondemo.data.remote.paging.NETWORK_PAGE_SIZE
import com.esaudev.pokemondemo.data.remote.paging.PokemonListPagingSource
import com.esaudev.pokemondemo.domain.model.Pokemon
import com.esaudev.pokemondemo.domain.model.PokemonDetail
import com.esaudev.pokemondemo.domain.repository.PokemonRepository
import com.esaudev.pokemondemo.util.Resource
import kotlinx.coroutines.flow.Flow

class PokemonRepositoryImpl(
    private val pokemonApi: PokemonApi
): PokemonRepository {

    override fun fetchPokemonWithPaging(): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PokemonListPagingSource(pokemonApi = pokemonApi)
            }
        ).flow
    }

    override suspend fun fetchPokemonDetailById(pokemonId: Int): Resource<PokemonDetail> {
        return try {
            val networkCall = pokemonApi.fetchPokemonDetailById(pokemonId = pokemonId)

            if (networkCall.isSuccessful) {
                val pokemonDetail = networkCall.body()
                Resource.Success(pokemonDetail!!.mapToDomain(pokemonId = pokemonId))
            } else {
                Resource.Error()
            }
        } catch (e: Exception) {
            Log.d("Exception", e.message.toString())
            Resource.Error()
        }
    }
}