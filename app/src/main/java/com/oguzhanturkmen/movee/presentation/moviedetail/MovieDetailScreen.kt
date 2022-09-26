package com.oguzhanturkmen.movee.presentation.moviedetail

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.oguzhanturkmen.movee.presentation.moviedetail.components.*


@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        state.movie?.let { movieDetail ->
            Column(modifier = Modifier.fillMaxSize()) {
                movieDetailImage(movieDetail = movieDetail)
                Column(
                    Modifier.padding(start = 32.dp, top = 8.dp, end = 32.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    movieDetailRating(movieDetail = movieDetail)
                    movieDetailTitle(movieDetail = movieDetail)
                    Row(horizontalArrangement = Arrangement.spacedBy(32.dp)) {
                        movieDetailRunTime(movieDetail = movieDetail)
                        movieDetailReleaseDate(movieDetail = movieDetail)
                    }
                    movieDetailOverview(movieDetail = movieDetail)
                }
            }
        }
    }
}
