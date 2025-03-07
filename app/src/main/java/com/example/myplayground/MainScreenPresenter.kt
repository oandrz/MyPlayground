package com.example.myplayground

import androidx.compose.runtime.Composable
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import kotlinx.android.parcel.Parcelize

@Parcelize
data object MainScreen : Screen {
    data class State(val name: String) : CircuitUiState

    sealed interface Event : CircuitUiEvent {
    }
}

class MainScreenPresenter : Presenter<MainScreen.State> {

    @Composable
    override fun present(): MainScreen.State {
        return MainScreen.State(name = "Oink")
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