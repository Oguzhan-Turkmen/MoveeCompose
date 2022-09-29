package com.oguzhanturkmen.movee.presentation.moviedetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.oguzhanturkmen.movee.presentation.moviedetail.components.*


@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel = hiltViewModel(),
) {
    val movieState = viewModel.state.value
    val castState = viewModel.moviecreditstate.value
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.CenterVertically)
    ) {
        movieState.movie?.let { movieDetail ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                movieDetailImage(movieDetail = movieDetail)
                Column(
                    Modifier
                        .padding(start = 32.dp, top = 8.dp, end = 32.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    movieDetailRating(movieDetail = movieDetail)
                    movieDetailTitle(movieDetail = movieDetail)
                    Row(horizontalArrangement = Arrangement.spacedBy(32.dp)) {
                        movieDetailRunTime(movieDetail = movieDetail)
                        movieDetailReleaseDate(movieDetail = movieDetail)
                    }
                    movieDetailOverview(movieDetail = movieDetail)

                    LazyRow(modifier = Modifier) {
                        items(castState.movieCredit) { cast ->
                            Column() {
                                castItem(cast = cast)
                            }
                        }
                    }
                }
            }

        }
    }
}
