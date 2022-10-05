package com.oguzhanturkmen.movee.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.oguzhanturkmen.movee.presentation.moviedetail.MovieDetailScreen
import com.oguzhanturkmen.movee.presentation.personDetail.PersonDetailScreen
import com.oguzhanturkmen.movee.presentation.search.SearchScreen
import com.oguzhanturkmen.movee.presentation.tvseriesdetail.TvSeriesDetailScreen

@Composable
fun SearchNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = SearchScreen.SearchHomeScreen.route
    ) {
        composable(
            route = SearchScreen.SearchHomeScreen.route
        ) {
            SearchScreen(navController = navController)
        }
        composable(
            route = SearchScreen.MovieDetailSearchScreen.route + "{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) {
            MovieDetailScreen(navController = navController)
        }
        composable(
            route = SearchScreen.PersonDetailSearchScreen.route + "{personId}",
            arguments = listOf(navArgument("personId") { type = NavType.IntType })
        ) {
            PersonDetailScreen(navController = navController)
        }
        composable(
            route = SearchScreen.TvSeriesSearchScreen.route + "{tvSeriesId}",
            arguments = listOf(navArgument("tvSeriesId") { type = NavType.IntType })

        ) {
            TvSeriesDetailScreen()
        }

    }
}

sealed class SearchScreen(val route: String) {
    object SearchHomeScreen : SearchScreen("search_home_screen")
    object MovieDetailSearchScreen : SearchScreen("movie_detail_search_screen")
    object TvSeriesSearchScreen : SearchScreen("tv_series_search_screen")
    object PersonDetailSearchScreen : SearchScreen("person_detail_search_screen")
}