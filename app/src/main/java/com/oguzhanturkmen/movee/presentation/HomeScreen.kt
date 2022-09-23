package com.oguzhanturkmen.movee.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.oguzhanturkmen.movee.presentation.movieDetail.MovieDetailScreen
import com.oguzhanturkmen.movee.presentation.nowPlayingMovie.HorizontalPagerWithOffsetTransition
import com.oguzhanturkmen.movee.presentation.nowPlayingMovie.components.gradient
import com.oguzhanturkmen.movee.presentation.popularMovie.PopularMoviesScreen
import com.oguzhanturkmen.movee.ui.theme.RatingBarColor


@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(
            route = Screen.HomeScreen.route
        ) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.MovieDetailScreen.route + " {movieId}",
            arguments = listOf(navArgument("moviId") { type = NavType.IntType })
        ) {
            MovieDetailScreen()
        }
    }
}


@Composable
fun HomeScreen(
    navController: NavController,
) {
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
            HorizontalPagerWithOffsetTransition(
                onClick = { navController.navigate(Screen.MovieDetailScreen.route) }
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


    val gradient = Brush.linearGradient(
        0.3f to Color.Green,
        1.0f to RatingBarColor,
        start = Offset(0.0f, 50.0f),
        end = Offset(0.0f, 50.0f)
    )

    @Composable
    fun gradiant() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawBehind {
                    drawRect(
                        brush = gradient,
                        topLeft = Offset(x = 0f, y = 0.dp.toPx()),
                        size = Size(500.dp.toPx(), 250.dp.toPx())
                    )
                }


        )
    }

}
