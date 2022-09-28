package com.oguzhanturkmen.movee.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.oguzhanturkmen.movee.presentation.TvSeriesScreen
import com.oguzhanturkmen.movee.presentation.tvseriesdetail.TvSeriesDetailScreen


@Composable
fun TvSeriesNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = TvSeriesScreen.TvSereiesHomeScreen.route
    ) {
        composable(
            route = TvSeriesScreen.TvSereiesHomeScreen.route
        ) {
            TvSeriesScreen(navController = navController)
        }
        composable(
            route = TvSeriesScreen.TvSeriesDetailScreen.route + "{tvSeriesId}",
            arguments = listOf(navArgument("tvSeriesId") { type = NavType.IntType })
        ) {
            TvSeriesDetailScreen()
        }
    }
}

sealed class TvSeriesScreen(val route: String) {
    object TvSereiesHomeScreen : TvSeriesScreen("home_screen")
    object TvSeriesDetailScreen : TvSeriesScreen("tvseries_detail_screen")
}