package com.oguzhanturkmen.movee.presentation.topratedtvseries

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.oguzhanturkmen.movee.presentation.topratedtvseries.components.topRatedTvSeriesItem


@Composable
fun TopRatedTvSeriesScreen(
    viewModel: TopRatedTvSeriesViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state.value
    Box(modifier = Modifier) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            modifier = Modifier
                .padding(start = 32.dp, end = 32.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.topRatedTvSerials) { tvseries ->
                topRatedTvSeriesItem(
                    tvSeries = tvseries,
                    modifier = Modifier,
                    onClick = {
                       // navController.navigate(TvSeriesScreen.TvSeriesDetailScreen.route + "${tvseries.id}")
                    }
                )
            }
        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

}