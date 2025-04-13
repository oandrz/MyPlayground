package com.example.list.data.response

import com.google.gson.annotations.SerializedName

data class PokemonDetailResponse(
    val id: Int,
    val name: String,
    val sprites: Sprites,
    val types: List<TypeSlot>
)

data class Sprites(
    @SerializedName("front_default") val frontDefault: String
)

data class TypeSlot(
    val slot: Int,
    val type: TypeName
)

data class TypeName(
    val name: String
)
