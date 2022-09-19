package com.oguzhanturkmen.movee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.oguzhanturkmen.movee.presentation.Screen
import com.oguzhanturkmen.movee.presentation.movieDetail.MovieDetailScreen
import com.oguzhanturkmen.movee.presentation.nowPlayingMovie.NowPlayingMoviesScreen
import com.oguzhanturkmen.movee.presentation.popularMovie.PopularMoviesScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Screen.NowPlayingMoviesScreen.route
            ) {
                composable(
                    route = Screen.PopularMoviesScreen.route
                ) {
                    PopularMoviesScreen(navController = navController)
                }
                composable(
                    route = Screen.MovieDetailScreen.route
                ) {
                    MovieDetailScreen()
                }
                composable(
                    route = Screen.NowPlayingMoviesScreen.route
                )
                {
                    NowPlayingMoviesScreen(navController = navController)
                }
            }
        }
    }
}



