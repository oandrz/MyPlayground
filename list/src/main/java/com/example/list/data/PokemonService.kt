package com.example.list.data

import com.example.list.data.response.PokemonListResponse
import retrofit2.http.GET

internal interface PokemonService {
    @GET("pokemon")
    suspend fun fetchListOfPokemon(): PokemonListResponse
}