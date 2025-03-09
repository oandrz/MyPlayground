package com.example.list

import androidx.compose.runtime.Composable
import com.example.list.api.ListScreen
import com.example.list.api.ListScreenPresenter
import com.slack.circuit.runtime.presenter.Presenter


internal class ListScreenPresenterImpl : Presenter<ListScreen.State>, ListScreenPresenter {

    @Composable
    override fun present(): ListScreen.State {
        return ListScreen.State()
    }
}