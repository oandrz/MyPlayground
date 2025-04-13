package com.example.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.list.api.ListScreen
import com.example.list.domain.Pokemon

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
    results: LazyPagingItems<Pokemon>,
    modifier: Modifier = Modifier,
    onItemClick: (String) -> Unit = {}
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(results.itemCount) { index ->
            val pokemon = results[index]

            if (pokemon != null) {
                Column(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFF4CAF50))
                        .clickable { onItemClick(pokemon.name) }
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(pokemon.pokemonDetail.frontSprite.frontDefault)
                            .crossfade(true)
                            .build(),
                        contentDescription = "${pokemon.name} sprite",
                        modifier = Modifier
                            .size(64.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = pokemon.name.replaceFirstChar { it.uppercase() },
                        color = Color.White
                    )
                }
            }
        }

        // Optional: Add loading state at the end
        when (results.loadState.append) {
            is LoadState.Loading -> {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

            is LoadState.Error -> {
                val error = results.loadState.append as LoadState.Error
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Text(
                        text = "Error: ${error.error.localizedMessage}",
                        modifier = Modifier.padding(16.dp),
                        color = Color.Red
                    )
                }
            }

            else -> Unit
        }
    }
}

