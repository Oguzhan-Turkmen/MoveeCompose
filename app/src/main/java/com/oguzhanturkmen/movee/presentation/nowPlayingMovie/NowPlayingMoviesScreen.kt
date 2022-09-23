package com.oguzhanturkmen.movee.presentation.nowPlayingMovie

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.oguzhanturkmen.movee.presentation.Screen
import com.oguzhanturkmen.movee.presentation.nowPlayingMovie.components.nowPlayingMovieImage
import com.oguzhanturkmen.movee.presentation.nowPlayingMovie.components.nowPlayingMovieTitle
import com.oguzhanturkmen.movee.presentation.nowPlayingMovie.components.nowPlayingMoviesItem
import com.oguzhanturkmen.movee.presentation.nowPlayingMovie.components.nowPlayingRating
import com.oguzhanturkmen.movee.ui.theme.RatingBarColor
import kotlin.math.absoluteValue


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
fun HorizontalPagerWithOffsetTransition(
    modifier: Modifier = Modifier.padding(top = 12.dp),
    viewmodel: NowPlayingMoviesViewModel = hiltViewModel(),
    onClick: (movieId: Int) -> Unit
) {
    val state = viewmodel.state.value
    HorizontalPager(
        count = viewmodel.state.value.nowPlayingMovies.size,
        contentPadding = PaddingValues(horizontal = 20.dp),
        modifier = modifier,
    ) { page ->
        Column(
            Modifier
                .graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                    lerp(
                        start = 0.90f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 0f)
                    )
                }
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }) {
                    onClick(state.nowPlayingMovies[page].id)
                }
                .fillMaxWidth()
                .aspectRatio(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            //verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            nowPlayingMovieImage(
                movie = state.nowPlayingMovies[page],
                modifier = Modifier.padding(start = 4.dp, top = 4.dp)
            )
            nowPlayingMovieTitle(movie = state.nowPlayingMovies[page], modifier = Modifier)
            nowPlayingRating(movie = state.nowPlayingMovies[page], modifier = Modifier)
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

        )
    }
    if (state.isLoading) {
        CircularProgressIndicator(modifier = Modifier)
    }

}

//
//@OptIn(ExperimentalPagerApi::class)
//@Composable
//fun horizontalPager(
//    viewmodel: NowPlayingMoviesViewModel = hiltViewModel(),
//) {
//    val value = viewmodel.state.value
//    val pagerState = rememberPagerState()
//    HorizontalPager(count = value.nowPlayingMovies.size) {
//        LazyRow(
//            Modifier.padding(top = 24.dp),
//            horizontalArrangement = Arrangement.spacedBy(24.dp)
//        ) {
//            items(value.nowPlayingMovies) { movie ->
//                nowPlayingMovieImage(movie = movie, modifier = Modifier)
//            }
//        }
//    }
//}

//@OptIn(ExperimentalPagerApi::class)
//@Composable
//fun horizontalPager(
//    viewmodel: NowPlayingMoviesViewModel = hiltViewModel(),
//) {
//    val value = viewmodel.state.value
//    val pagerState = rememberPagerState()
//    HorizontalPager(count = 20) {
//        Row(
//            horizontalArrangement = Arrangement.Center,
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            LazyRow(
//                Modifier.padding(top = 24.dp),
//                horizontalArrangement = Arrangement.spacedBy(24.dp)
//            ) {
//                items(value.nowPlayingMovies) { movie ->
//                    nowPlayingMovieImage(movie = movie, modifier = Modifier)
//                }
//            }
//        }
//
//    }
//}

//@Composable
//fun SnappyLazyRow(
//    viewmodel: NowPlayingMoviesViewModel = hiltViewModel(),
//    navController: NavController
//) {
//    val listState = rememberLazyListState()
//    val coroutineScope = rememberCoroutineScope()
//    val state = viewmodel.state.value
//
//    LazyRow(
//        state = listState,
//        modifier = Modifier
//            .padding(15.dp),
//        horizontalArrangement = Arrangement.spacedBy(40.dp),
//        content = {
//            items(state.nowPlayingMovies) { movie ->
//                nowPlayingMovieImage(movie = movie, modifier = Modifier)
//
//                if (!listState.isScrollInProgress) {
//                    if (listState.isHalfPastItemLeft())
//                        coroutineScope.scrollBasic(listState, left = true)
//                    else
//                        coroutineScope.scrollBasic(listState)
//
//                    if (listState.isHalfPastItemRight())
//                        coroutineScope.scrollBasic(listState)
//                    else
//                        coroutineScope.scrollBasic(listState, left = true)
//                }
//            }
//        })
//}
//
//private fun CoroutineScope.scrollBasic(listState: LazyListState, left: Boolean = false) {
//    launch {
//        val pos = if (left) listState.firstVisibleItemIndex else listState.firstVisibleItemIndex + 1
//        listState.animateScrollToItem(pos)
//    }
//}
//
//@Composable
//private fun LazyListState.isHalfPastItemRight(): Boolean {
//    return firstVisibleItemScrollOffset > 500
//}
//
//@Composable
//private fun LazyListState.isHalfPastItemLeft(): Boolean {
//    return firstVisibleItemScrollOffset <= 500
//}

