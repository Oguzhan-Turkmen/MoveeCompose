package com.oguzhanturkmen.movee.presentation.nowPlayingMovie

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
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
import com.oguzhanturkmen.movee.presentation.Screen
import com.oguzhanturkmen.movee.presentation.nowPlayingMovie.components.nowPlayingMoviesItem


@Composable
fun NowPlayingMoviesScreen(
    viewmodel: NowPlayingMoviesViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewmodel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        LazyRow(
            modifier = Modifier
                .padding(start = 4.dp, end = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(state.nowPlayingMovies) { movie ->
                nowPlayingMoviesItem(
                    movie = movie,
                    modifier = Modifier,
                    onClick = {
                        navController.navigate(Screen.MovieDetailScreen.route)
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