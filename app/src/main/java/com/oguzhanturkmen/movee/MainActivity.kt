package com.oguzhanturkmen.movee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.oguzhanturkmen.movee.presentation.Navigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation()
            //gradiant()

            /*
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
               }*/
        }
    }
}



