package com.example.myplayground.presenter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myplayground.model.Menu
import com.example.myplayground.ui.theme.MyPlaygroundTheme
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui

@Composable
fun MainUIComposable(state: MainScreen.State, modifier: Modifier = Modifier) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        MenuList(state.menus, modifier.padding(innerPadding)) {
            state.eventSink(MainScreen.Event.MenuClicked(it))
        }
    }
}

@Composable
fun MenuList(menus: List<Menu>, modifier: Modifier = Modifier, onItemClick: (Menu) -> Unit = {}) {
    LazyColumn (
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        items(menus) {
            MenuItem(menu = it, onClick = onItemClick)
        }
    }
}

@Composable
fun MenuItem(menu: Menu, modifier: Modifier = Modifier, onClick: (Menu) -> Unit) {
    Card(
        modifier = modifier.padding(8.dp)
            .fillMaxWidth()
            .height(50.dp)
            .clickable {
                onClick(menu)
            }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = menu.content,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyPlaygroundTheme {
        MenuList(Menu.entries)
    }
}

class MainUIFactory : Ui.Factory {
    override fun create(screen: Screen, context: CircuitContext): Ui<*>? = when (screen) {
        is MainScreen -> ui<MainScreen.State> { state, modifier ->
            MainUIComposable(state, modifier)
        }
        else -> null
    }
}