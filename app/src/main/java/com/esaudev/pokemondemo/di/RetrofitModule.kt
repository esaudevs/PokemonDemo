package com.esaudev.pokemondemo.di

import com.esaudev.pokemondemo.data.remote.api.PokemonApi
import com.esaudev.pokemondemo.data.remote.constants.RetrofitConstants.POKE_API_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun providePokemonApi(
        client: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): PokemonApi {
        return Retrofit.Builder()
            .baseUrl(POKE_API_BASE_URL)
            .addConverterFactory(converterFactory)
            .client(client)
            .build()
            .create()
    }

}