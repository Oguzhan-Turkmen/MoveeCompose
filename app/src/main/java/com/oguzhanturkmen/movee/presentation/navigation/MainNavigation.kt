package com.oguzhanturkmen.movee.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.oguzhanturkmen.movee.presentation.MoviesScreen
import com.oguzhanturkmen.movee.presentation.TvSeriesScreen
import com.oguzhanturkmen.movee.presentation.login.loginScreen
import com.oguzhanturkmen.movee.presentation.map.MapScreen
import com.oguzhanturkmen.movee.presentation.moviedetail.MovieDetailScreen
import com.oguzhanturkmen.movee.presentation.personDetail.PersonDetailScreen
import com.oguzhanturkmen.movee.presentation.profile.ProfileScreen
import com.oguzhanturkmen.movee.presentation.search.SearchScreen
import com.oguzhanturkmen.movee.presentation.signin.signInScreen
import com.oguzhanturkmen.movee.presentation.tvseriesdetail.TvSeriesDetailScreen

@Composable
fun MainNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = MainScreens.MainLoginScreen.route
    ) {
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
            TvSeriesDetailScreen(navController = navController)
        }
        composable(
            route = MainScreens.SearchHomeScreen.route
        ) {
            SearchScreen(navController = navController)
        }
        composable(
            route = MainScreens.MainMapScreen.route,
        ) {
            MapScreen()
        }
        composable(
            route = MainScreens.MainProfileScreen.route
        ) {
            ProfileScreen(navController = navController)
        }
        composable(
            route = MainScreens.MainLoginScreen.route
        ) {
            loginScreen(navController = navController)
        }
        composable(
            route = MainScreens.MainSignInScreen.route
        ) {
            signInScreen(navController = navController)
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
    object MainProfileScreen : MainScreens("main_profile_screen")
    object MainMapScreen : MainScreens("main_map_screen")
    object MainLoginScreen : MainScreens("main_login_screen")
    object MainSignInScreen : MainScreens("main_sign_in_screen")
}