package com.esaudev.pokemondemo.util

sealed class Resource <out T> {

    data class Success<T>(val data: T) : Resource<T>()
    data class Error(val error: String = "") : Resource<Nothing>()
    object Loading : Resource<Nothing>()

}