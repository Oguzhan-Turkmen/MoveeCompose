package com.oguzhanturkmen.movee.presentation

sealed class Screen(val route: String) {
    object MovieDetailScreen : Screen("movie_detail_screen")
    object HomeScreen : Screen("home_screen")
}