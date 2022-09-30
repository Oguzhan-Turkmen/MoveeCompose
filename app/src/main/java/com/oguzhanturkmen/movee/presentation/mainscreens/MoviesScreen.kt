package com.oguzhanturkmen.movee.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.oguzhanturkmen.movee.presentation.navigation.MoviesScreen
import com.oguzhanturkmen.movee.presentation.nowPlayingMovie.nowPlayingMoviesHorizontalPager
import com.oguzhanturkmen.movee.presentation.popularMovie.PopularMoviesViewModel
import com.oguzhanturkmen.movee.presentation.popularMovie.components.PopularMoviesItem
import com.oguzhanturkmen.movee.ui.theme.RatingBarColor

/*@Composable
fun MoviesScreen(
    navController: NavController,
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(scaffoldState = scaffoldState) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawBehind {
                    drawRect(
                        brush = gradient,
                        topLeft = Offset(x = 0f, y = 0.dp.toPx()),
                        size = Size(500.dp.toPx(), 250.dp.toPx())
                    )
                }) {
            Column {
                Text(
                    modifier = Modifier
                        .padding(top = 32.dp, start = 32.dp),
                    text = "Movies",
                    style = TextStyle(fontSize = 34.sp),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                nowPlayingMoviesHorizontalPager(
                    onClick = { navController.navigate(MoviesScreen.MovieDetailScreen.route + it) }
                )
                Text(
                    modifier = Modifier
                        .padding(start = 32.dp),
                    text = "Popular",
                    style = TextStyle(fontSize = 24.sp),
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                PopularMoviesScreen(navController = navController)
            }
        }
    }
}*/
@Composable
fun MoviesScreen(
    navController: NavController,
    modifier: Modifier = Modifier.padding(top = 12.dp),
    viewModel: PopularMoviesViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .drawBehind {
                drawRect(
                    brush = gradient,
                    topLeft = Offset(x = 0f, y = 0.dp.toPx()),
                    size = Size(500.dp.toPx(), 250.dp.toPx())
                )
            }) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .drawBehind {
                    drawRect(
                        brush = gradient,
                        topLeft = Offset(x = 0f, y = 0.dp.toPx()),
                        size = Size(500.dp.toPx(), 250.dp.toPx())
                    )
                },
            verticalArrangement = Arrangement.spacedBy(8.dp),

            ) {
            item {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier
                            .padding(top = 32.dp, start = 32.dp),
                        text = "Movies",
                        style = TextStyle(fontSize = 34.sp),
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    nowPlayingMoviesHorizontalPager(
                        onClick = {
                            navController.navigate(MoviesScreen.MovieDetailScreen.route + it)
                        })
                }
            }
            item {
                Text(
                    modifier = Modifier
                        .padding(start = 32.dp),
                    text = "Popular",
                    style = TextStyle(fontSize = 24.sp),
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
            items(state.popularMovies) { movie ->
                PopularMoviesItem(
                    movie = movie,
                    modifier = Modifier,
                    onClick = {
                        navController.navigate(MoviesScreen.MovieDetailScreen.route + "${movie.id}")
                    }
                )
            }
        }
    }
}

@Composable
fun nestedScreen(
    navController: NavController,
    modifier: Modifier = Modifier.padding(top = 12.dp),
    viewModel: PopularMoviesViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .drawBehind {
                drawRect(
                    brush = gradient,
                    topLeft = Offset(x = 0f, y = 0.dp.toPx()),
                    size = Size(500.dp.toPx(), 250.dp.toPx())
                )
            }) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            item {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier
                            .padding(top = 32.dp, start = 32.dp),
                        text = "Movies",
                        style = TextStyle(fontSize = 34.sp),
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    nowPlayingMoviesHorizontalPager(
                        onClick = {
                            //navController.navigate(MoviesScreen.MovieDetailScreen.route + it)

                        })
                }
            }
            item {
                Text(
                    modifier = Modifier
                        .padding(start = 32.dp),
                    text = "Popular",
                    style = TextStyle(fontSize = 24.sp),
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
            items(state.popularMovies) { movie ->
                PopularMoviesItem(
                    movie = movie,
                    modifier = Modifier,
                    onClick = {
                        navController.navigate(MoviesScreen.MovieDetailScreen.route + "${movie.id}")
                    }
                )
            }
        }
    }
}


val gradient = Brush.linearGradient(
    0.3f to Color.Green,
    1.0f to RatingBarColor,
    start = Offset(0.0f, 50.0f),
    end = Offset(0.0f, 50.0f)
)




