package com.example.myplayground.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.list.api.ListScreenPresenter
import com.example.list.api.ListScreenUI
import com.example.list.data.ListScreenRepository
import com.example.myplayground.ui.theme.MyPlaygroundTheme
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var repository: ListScreenRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val circuit = Circuit.Builder()
            .addUiFactory(MainUIFactory())
            .addPresenterFactory(MainScreenPresenter.Factory())
            .addUiFactory(ListScreenUI.Factory())
            .addPresenterFactory(ListScreenPresenter.Factory(repository))
            .build()
        setContent {
            MyPlaygroundTheme {
                val backStack = rememberSaveableBackStack(MainScreen)
                val navigator = rememberCircuitNavigator(backStack)

                CircuitCompositionLocals(circuit) {
                    NavigableCircuitContent(navigator = navigator, backStack = backStack)
                }
            }
        }
    }
}