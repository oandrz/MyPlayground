package com.example.list.data.response

internal data class PokemonListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonResponse>,
)