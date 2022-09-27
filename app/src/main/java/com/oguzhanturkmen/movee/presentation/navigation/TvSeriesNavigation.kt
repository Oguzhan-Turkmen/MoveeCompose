package com.oguzhanturkmen.movee.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.oguzhanturkmen.movee.presentation.TvSeriesScreen

sealed class TvSeriesScreen(val route: String) {
    object TvSerisHomeScreen : TvSeriesScreen("home_screen")
}

@Composable
fun TvSeriesNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = TvSeriesScreen.TvSerisHomeScreen.route
    ) {
        composable(
            route = TvSeriesScreen.TvSerisHomeScreen.route
        ) {
            TvSeriesScreen(navController = navController)
        }
    }
}