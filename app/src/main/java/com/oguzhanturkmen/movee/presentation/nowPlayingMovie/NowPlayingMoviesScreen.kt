package com.oguzhanturkmen.movee.presentation.nowPlayingMovie

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.oguzhanturkmen.movee.presentation.Screen
import com.oguzhanturkmen.movee.presentation.nowPlayingMovie.components.nowPlayingMovieImage
import com.oguzhanturkmen.movee.presentation.nowPlayingMovie.components.nowPlayingMoviesItem
import com.oguzhanturkmen.movee.ui.theme.RatingBarColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun NowPlayingMoviesScreen(
    viewmodel: NowPlayingMoviesViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewmodel.state.value
    //modifier fillmaxsize sildim
    Box(
        modifier = Modifier
    ) {
        Box(
            modifier = Modifier
                .background(RatingBarColor)
                .fillMaxWidth()
                .height(200.dp)
        ) {

        }
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

@OptIn(ExperimentalPagerApi::class)
@Composable
fun horizontalPager(
    viewmodel: NowPlayingMoviesViewModel = hiltViewModel(),
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        val value = viewmodel.state.value
        val pagerState = rememberPagerState()
        HorizontalPager(count = 20) {
            LazyRow(
                Modifier.padding(top = 140.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                items(value.nowPlayingMovies) { movie ->
                    nowPlayingMovieImage(movie = movie, modifier = Modifier)
                }
            }
        }
    }
}

@Composable
fun SnappyLazyRow(
    viewmodel: NowPlayingMoviesViewModel = hiltViewModel(),
    navController: NavController
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val state = viewmodel.state.value

    LazyRow(
        state = listState,
        modifier = Modifier,
        horizontalArrangement = Arrangement.spacedBy(40.dp),
        content = {
            items(state.nowPlayingMovies) { movie ->
                nowPlayingMovieImage(movie = movie, modifier = Modifier)

                if (!listState.isScrollInProgress) {
                    if (listState.isHalfPastItemLeft())
                        coroutineScope.scrollBasic(listState, left = true)
                    else
                        coroutineScope.scrollBasic(listState)

                    if (listState.isHalfPastItemRight())
                        coroutineScope.scrollBasic(listState)
                    else
                        coroutineScope.scrollBasic(listState, left = true)
                }
            }
        })
}

private fun CoroutineScope.scrollBasic(listState: LazyListState, left: Boolean = false) {
    launch {
        val pos = if (left) listState.firstVisibleItemIndex else listState.firstVisibleItemIndex + 1
        listState.animateScrollToItem(pos)
    }
}

@Composable
private fun LazyListState.isHalfPastItemRight(): Boolean {
    return firstVisibleItemScrollOffset > 500
}

@Composable
private fun LazyListState.isHalfPastItemLeft(): Boolean {
    return firstVisibleItemScrollOffset <= 500
}

