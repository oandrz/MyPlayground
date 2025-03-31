package com.example.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.list.api.ListScreen

@Composable
internal fun ListScreenUIComposable(state: ListScreen.State, modifier: Modifier = Modifier) {
    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        PokemonList(
            modifier = Modifier.padding(innerPadding),
            results = state.results
        )
    }
}

@Composable
internal fun PokemonList(
    results: List<String>,
    modifier: Modifier = Modifier,
    onItemClick: (String) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        items(results) {
            Text(it, modifier = Modifier.padding(8.dp))
        }
    }
}