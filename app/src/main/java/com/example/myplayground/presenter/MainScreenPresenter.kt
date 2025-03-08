package com.example.myplayground.presenter

import androidx.compose.runtime.Composable
import com.example.myplayground.model.Menu
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import kotlinx.android.parcel.Parcelize

@Parcelize
data object MainScreen : Screen {
    data class State(val menus: List<Menu>, val eventSink: (Event) -> Unit) : CircuitUiState

    sealed interface Event : CircuitUiEvent {
        data class MenuClicked(val menu: Menu) : Event
    }
}

class MainScreenPresenter : Presenter<MainScreen.State> {

    @Composable
    override fun present(): MainScreen.State {
        return MainScreen.State(
            menus = Menu.entries
        ) { event ->
            when (event) {
                is MainScreen.Event.MenuClicked -> println("testing andre getting clicked ${event.menu.content}")
            }
        }
    }

    class Factory : Presenter.Factory {
        override fun create(
            screen: Screen,
            navigator: Navigator,
            context: CircuitContext
        ): Presenter<*>? = when (screen) {
            is MainScreen -> MainScreenPresenter()
            else -> null
        }
    }
}