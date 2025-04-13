package com.example.list.api

import androidx.paging.compose.LazyPagingItems
import com.example.list.ListScreenPresenterImpl
import com.example.list.data.ListScreenRepository
import com.example.list.domain.Pokemon
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject

@Parcelize
data object ListScreen : Screen {
    data class State(
        val results: LazyPagingItems<Pokemon>,
        val eventSink: (Event) -> Unit
    ) : CircuitUiState


    sealed interface Event : CircuitUiEvent {

    }
}

interface ListScreenPresenter {

    class Factory @Inject constructor(
        private val listScreenRepository: ListScreenRepository
    ) : Presenter.Factory {
        override fun create(
            screen: Screen,
            navigator: Navigator,
            context: CircuitContext
        ): Presenter<*>? = when (screen) {
            is ListScreen -> ListScreenPresenterImpl(listScreenRepository)
            else -> null
        }
    }
}