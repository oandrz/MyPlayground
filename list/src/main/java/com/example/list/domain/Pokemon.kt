package com.example.list.domain

data class Pokemon(
    val name: String,
    val pokemonDetail: PokemonDetail
)

data class PokemonDetail(
    val url: String
)