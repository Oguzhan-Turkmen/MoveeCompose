package com.oguzhanturkmen.movee.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Movies.route
    ) {
        composable(route = BottomBarScreen.Movies.route) {
            //MainNavigation()
            MoviesNavigation()
        }
        composable(route = BottomBarScreen.Series.route) {
            TvSeriesNavigation()
        }
        composable(route = BottomBarScreen.Search.route) {
            SearchNavigation()
        }
    }

}