package com.oguzhanturkmen.movee.presentation.popular_movie

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.oguzhanturkmen.movee.domain.model.Movies

@Composable
fun PopularMovieScreen(
    navController: NavController,
    viewModel: PopularMoviesViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
    LazyColumn{
        items(state.popularMovies){ item: Movies ->
            
        }
    }

    }
}