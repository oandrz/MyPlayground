package com.example.list.data

import com.example.core_api.MyPlaygroundNetwork
import com.example.list.domain.Pokemon
import com.example.list.domain.PokemonDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface ListScreenRepository {
    fun fetchPokemonList(): Flow<List<Pokemon>>
}

internal class ListScreenRepositoryImpl @Inject constructor(
    private val myPlaygroundNetwork: MyPlaygroundNetwork
) : ListScreenRepository {

    override fun fetchPokemonList(): Flow<List<Pokemon>> = flow {
        val response = getPokemonService().fetchListOfPokemon()
        emit(
            response.results.map {
                Pokemon(
                    name = it.name,
                    pokemonDetail = PokemonDetail(
                        url = it.url
                    )
                )
            }
        )
    }

    private fun getPokemonService(): PokemonService =
        myPlaygroundNetwork.fromService(PokemonService::class.java)
}