package com.esaudev.pokemondemo.data.remote.api

import com.esaudev.pokemondemo.data.remote.dto.PokemonDetailDto
import com.esaudev.pokemondemo.data.remote.dto.PokemonDto
import com.esaudev.pokemondemo.data.remote.wrapper.PokemonResponseWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    @GET("/api/v2/pokemon/")
    suspend fun fetchPokemonPagingList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<PokemonResponseWrapper<List<PokemonDto>>>

    @GET("https://pokeapi.co/api/v2/pokemon/{pokemonId}")
    suspend fun fetchPokemonDetailById(
        @Path("pokemonId") pokemonId: Int
    ): Response<PokemonDetailDto>

}