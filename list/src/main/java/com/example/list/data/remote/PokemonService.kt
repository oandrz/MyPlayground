package com.example.list.data.remote

import com.example.list.data.response.PokemonDetailResponse
import com.example.list.data.response.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface PokemonService {
    @GET("pokemon")
    suspend fun fetchListOfPokemon(): PokemonListResponse

    @GET("pokemon")
    suspend fun getPokemonListSelfControl(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): PokemonListResponse


    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(@Path("name") name: String): PokemonDetailResponse
}