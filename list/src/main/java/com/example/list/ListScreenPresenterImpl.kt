package com.example.list

import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.list.api.ListScreen
import com.example.list.api.ListScreenPresenter
import com.example.list.data.ListScreenRepository
import com.slack.circuit.runtime.presenter.Presenter

internal class ListScreenPresenterImpl(
    private val listScreenRepository: ListScreenRepository
) : Presenter<ListScreen.State>, ListScreenPresenter {

    @Composable
    override fun present(): ListScreen.State {
        val results = listScreenRepository.getPokemonPager().collectAsLazyPagingItems()
        return ListScreen.State(results) { }
    }
}