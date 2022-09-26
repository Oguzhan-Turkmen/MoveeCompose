package com.oguzhanturkmen.movee.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.oguzhanturkmen.movee.presentation.MoviesScreen
import com.oguzhanturkmen.movee.presentation.moviedetail.MovieDetailScreen

@Composable
fun MoviesNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(
            route = Screen.HomeScreen.route
        ) {
            MoviesScreen(navController = navController)
        }
        composable(
            route = Screen.MovieDetailScreen.route + "{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) {
            MovieDetailScreen()
        }
    }
}

sealed class Screen(val route: String) {
    object MovieDetailScreen : Screen("movie_detail_screen")
    object HomeScreen : Screen("home_screen")
}