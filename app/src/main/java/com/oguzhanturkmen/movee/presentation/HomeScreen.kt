package com.oguzhanturkmen.movee.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.oguzhanturkmen.movee.presentation.movieDetail.MovieDetailScreen
import com.oguzhanturkmen.movee.presentation.nowPlayingMovie.NowPlayingMoviesScreen
import com.oguzhanturkmen.movee.presentation.popularMovie.PopularMoviesScreen


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
            route = Screen.PopularMoviesScreen.route
        ) {
            PopularMoviesScreen(navController = navController)
        }
        composable(
            route = Screen.MovieDetailScreen.route
        ) {
            MovieDetailScreen()
        }
        composable(
            route = Screen.NowPlayingMoviesScreen.route
        )
        {
            NowPlayingMoviesScreen(navController = navController)
        }
    }
}


@Composable
fun HomeScreen(
    navController: NavController,
) {
    Column() {
        Text(
            text = "Now Playing",
            style = TextStyle(fontSize = 24.sp),
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
        NowPlayingMoviesScreen(navController = navController)
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Popular Movies",
            style = TextStyle(fontSize = 24.sp),
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
        PopularMoviesScreen(navController = navController)
    }


}


//@Preview
//@Composable
//fun previewHomeScreen() {
//    HomeScreen(
//        movie = Movie(
//            1,
//            "Joker",
//            "asd",
//            5.3,
//            "2022-08-11"
//        ),
//        modifier = Modifier
//    )
//}