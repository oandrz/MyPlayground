package com.example.list.api

import com.example.list.ListScreenPresenterImpl
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import kotlinx.android.parcel.Parcelize

@Parcelize
data object ListScreen : Screen {
    class State() : CircuitUiState
    sealed interface Event : CircuitUiEvent {

    }
}

interface ListScreenPresenter {

    class Factory : Presenter.Factory {
        override fun create(
            screen: Screen,
            navigator: Navigator,
            context: CircuitContext
        ): Presenter<*>? = when (screen) {
            is ListScreen -> ListScreenPresenterImpl()
            else -> null
        }
    }
}