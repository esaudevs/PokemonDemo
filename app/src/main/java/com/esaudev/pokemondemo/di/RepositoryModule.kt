package com.esaudev.pokemondemo.di

import com.esaudev.pokemondemo.data.remote.api.PokemonApi
import com.esaudev.pokemondemo.data.repository.PokemonRepositoryImpl
import com.esaudev.pokemondemo.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePokemonRepository(
        pokemonApi: PokemonApi
    ): PokemonRepository = PokemonRepositoryImpl(
        pokemonApi = pokemonApi
    )

}