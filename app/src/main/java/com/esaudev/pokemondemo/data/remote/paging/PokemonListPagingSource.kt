package com.esaudev.pokemondemo.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.esaudev.pokemondemo.data.remote.api.PokemonApi
import com.esaudev.pokemondemo.data.remote.mappers.mapToDomain
import com.esaudev.pokemondemo.domain.model.Pokemon

const val NETWORK_PAGE_SIZE = 20
private const val INITIAL_LOAD_SIZE = 1

class PokemonListPagingSource(
    private val pokemonApi: PokemonApi
): PagingSource<Int, Pokemon>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val position = params.key ?: INITIAL_LOAD_SIZE
        val offset = if (params.key != null) ((position -1)* NETWORK_PAGE_SIZE) + 1 else INITIAL_LOAD_SIZE

        return try {
            val response = pokemonApi.fetchPokemonPagingList(
                offset = offset,
                limit = params.loadSize
            )

            val pokemonList = response.body()?.results?.mapToDomain()?: emptyList()
            val nextKey = if (pokemonList.isEmpty()) {
                null
            } else {
                position + (params.loadSize/ NETWORK_PAGE_SIZE)
            }

            LoadResult.Page(
                data = pokemonList,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }




}