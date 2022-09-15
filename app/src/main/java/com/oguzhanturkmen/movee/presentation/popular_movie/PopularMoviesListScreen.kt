package com.oguzhanturkmen.movee.presentation.popular_movie

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.oguzhanturkmen.movee.presentation.popular_movie.components.PopularMoviesItem

@Composable
fun PopularMoviesListScreen(
) {
    val viewModel: PopularMoviesViewModel = viewModel()
    val state = viewModel.state.value
    Log.d("sadas", viewModel.toString())
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(state.popularMovies) { movie ->
                PopularMoviesItem(movie = movie, modifier = Modifier)
            }
        }
    }
}