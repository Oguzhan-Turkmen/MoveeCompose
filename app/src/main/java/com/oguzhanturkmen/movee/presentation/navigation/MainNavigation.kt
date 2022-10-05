package com.oguzhanturkmen.movee.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.oguzhanturkmen.movee.presentation.MoviesScreen
import com.oguzhanturkmen.movee.presentation.TvSeriesScreen
import com.oguzhanturkmen.movee.presentation.moviedetail.MovieDetailScreen
import com.oguzhanturkmen.movee.presentation.personDetail.PersonDetailScreen
import com.oguzhanturkmen.movee.presentation.search.SearchScreen
import com.oguzhanturkmen.movee.presentation.tvseriesdetail.TvSeriesDetailScreen

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MainScreens.MainMovieScreen.route
    ) {
        composable(
            route = MainScreens.BottomNavGraph.route
        ) {
            BottomNavGraph(navController = navController)
        }
        composable(
            route = MainScreens.MainMovieScreen.route
        ) {
            MoviesScreen(navController = navController)
        }
        composable(
            route = MainScreens.MainMovieDetailScreen.route + "{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) {
            MovieDetailScreen(navController = navController)
        }
        composable(
            route = MainScreens.MainPersonDetailScreen.route + "{personId}",
            arguments = listOf(navArgument("personId") { type = NavType.IntType })
        ) {
            PersonDetailScreen(navController = navController)
        }
        composable(
            route = MainScreens.MainTvSeriesScreen.route
        ) {
            TvSeriesScreen(navController = navController)
        }
        composable(
            route = MainScreens.MainTvSeriesDetailScreen.route + "{tvSeriesId}",
            arguments = listOf(navArgument("tvSeriesId") { type = NavType.IntType })
        ) {
            TvSeriesDetailScreen()
        }
        composable(
            route = MainScreens.SearchHomeScreen.route
        ) {
            SearchScreen(navController = navController)
        }
    }
}

sealed class MainScreens(val route: String) {
    object SearchHomeScreen : MainScreens("search_home_screen")
    object MainMovieDetailScreen : MainScreens("main_movie_detail_screen")
    object MainMovieScreen : MainScreens("main_movie_screen")
    object MainTvSeriesScreen : MainScreens("main_tv_series_screen")
    object MainTvSeriesDetailScreen : MainScreens("main_tv_series_detail_screen")
    object MainPersonDetailScreen : MainScreens("main_person_detail_screen")
    object BottomNavGraph : MainScreens("bottom_nav_graph")
}