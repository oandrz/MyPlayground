package com.example.list.data.page

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.list.data.remote.PokemonService
import com.example.list.domain.Pokemon
import com.example.list.domain.PokemonDetail
import com.example.list.domain.PokemonSprite
import com.example.list.domain.PokemonType

internal const val PAGE_SIZE = 20

internal class PokemonPagingSource(
    private val api: PokemonService
) : PagingSource<Int, Pokemon>() {

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? =
        state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val page = params.key ?: 0
        return try {
            val response = api.getPokemonListSelfControl(
                offset = page * PAGE_SIZE, limit = PAGE_SIZE
            )

            if (response.results.isEmpty()) {
                return LoadResult.Page(
                    data = emptyList(),
                    prevKey = if (page == 0) null else page - 1,
                    nextKey = null
                )
            }

            val detailedList = response.results.map { basic ->
                api.getPokemonDetail(basic.name)
            }

            LoadResult.Page(
                data = detailedList
                    .map {
                        Pokemon(
                            name = it.name,
                            pokemonDetail = PokemonDetail(
                                frontSprite = PokemonSprite(it.sprites.frontDefault),
                                type = PokemonType(
                                    slot = it.types.first().slot,
                                    name = it.types.first().type.name
                                )
                            )
                        )
                    },
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (response.results.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}