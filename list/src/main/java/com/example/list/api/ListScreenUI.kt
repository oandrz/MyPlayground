package com.example.list.api

import com.example.list.ListScreenUIComposable
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui

interface ListScreenUI {

    class Factory : Ui.Factory {
        override fun create(screen: Screen, context: CircuitContext): Ui<*>? = when (screen) {
            is ListScreen -> ui<ListScreen.State> { state, modifier ->
                ListScreenUIComposable(state, modifier)
            }
            else -> null
        }
    }
}