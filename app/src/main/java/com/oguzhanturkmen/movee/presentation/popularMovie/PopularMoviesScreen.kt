package com.oguzhanturkmen.movee.presentation.popularMovie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.oguzhanturkmen.movee.presentation.popularMovie.components.PopularMoviesItem

@Composable
fun PopularMoviesScreen(
    viewModel: PopularMoviesViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state.value
    Box(modifier = Modifier) {
        LazyColumn(
            modifier = Modifier
                .padding(start = 32.dp, end = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(state.popularMovies) { movie ->
                PopularMoviesItem(
                    movie = movie,
                    modifier = Modifier,
                    onClick = {
                       // navController.navigate(MoviesScreen.MovieDetailScreen.route + "${movie.id}")
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