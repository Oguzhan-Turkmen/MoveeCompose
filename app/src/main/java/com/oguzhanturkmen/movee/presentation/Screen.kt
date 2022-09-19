package com.oguzhanturkmen.movee.presentation

sealed class Screen(val route: String) {
    object PopularMoviesScreen : Screen("popular_movie_screen")
    object MovieDetailScreen : Screen("movie_detail_screen")
    object NowPlayingMoviesScreen : Screen("now_playing_movie_screen")
    object HomeScreen : Screen("home_screen")
}