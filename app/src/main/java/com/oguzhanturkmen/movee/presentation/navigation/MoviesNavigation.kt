package com.oguzhanturkmen.movee.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.oguzhanturkmen.movee.presentation.MoviesScreen
import com.oguzhanturkmen.movee.presentation.moviedetail.MovieDetailScreen
import com.oguzhanturkmen.movee.presentation.personDetail.PersonDetailScreen

@Composable
fun MoviesNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MoviesScreen.MoviesHomeScreen.route
    ) {
        composable(
            route = MoviesScreen.MoviesHomeScreen.route
        ) {
            MoviesScreen(navController = navController)
        }
        composable(
            route = MoviesScreen.MovieDetailScreen.route + "{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) {
            MovieDetailScreen(navController = navController)
        }
        composable(
            route = MoviesScreen.PersonDetailScreen.route + "{personId}",
            arguments = listOf(navArgument("personId") { type = NavType.IntType })
        ) {
            PersonDetailScreen(navController = navController)
        }

    }
}

sealed class MoviesScreen(val route: String) {
    object MovieDetailScreen : MoviesScreen("movie_detail_screen")
    object MoviesHomeScreen : MoviesScreen("home_screen")
    object PersonDetailScreen : MoviesScreen("person_detail_screen")
}