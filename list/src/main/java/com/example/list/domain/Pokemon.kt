package com.example.list.domain

data class Pokemon(
    val name: String,
    val pokemonDetail: PokemonDetail
)

data class PokemonDetail(
    val frontSprite: PokemonSprite,
    val type: PokemonType
)

data class PokemonSprite(
    val frontDefault: String
)

data class PokemonType(
    val slot: Int,
    val name: String
)

