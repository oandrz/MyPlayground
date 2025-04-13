package com.example.list.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core_api.MyPlaygroundNetwork
import com.example.list.data.page.PAGE_SIZE
import com.example.list.data.page.PokemonPagingSource
import com.example.list.data.remote.PokemonService
import com.example.list.domain.Pokemon
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ListScreenRepository {
    fun getPokemonPager(): Flow<PagingData<Pokemon>>
}

internal class ListScreenRepositoryImpl @Inject constructor(
    private val myPlaygroundNetwork: MyPlaygroundNetwork
) : ListScreenRepository {
    override fun getPokemonPager(): Flow<PagingData<Pokemon>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE)
    ) {
        PokemonPagingSource(myPlaygroundNetwork.fromService(PokemonService::class.java))
    }.flow
}