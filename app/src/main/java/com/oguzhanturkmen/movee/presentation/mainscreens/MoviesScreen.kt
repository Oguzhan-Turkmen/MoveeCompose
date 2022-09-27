package com.oguzhanturkmen.movee.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.oguzhanturkmen.movee.presentation.navigation.MoviesScreen
import com.oguzhanturkmen.movee.presentation.nowPlayingMovie.components.gradient
import com.oguzhanturkmen.movee.presentation.nowPlayingMovie.nowPlayingMoviesHorizontalPager
import com.oguzhanturkmen.movee.presentation.popularMovie.PopularMoviesScreen

@Composable
fun MoviesScreen(
    navController: NavController,
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(scaffoldState = scaffoldState) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawBehind {
                    drawRect(
                        brush = gradient,
                        topLeft = Offset(x = 0f, y = 0.dp.toPx()),
                        size = Size(500.dp.toPx(), 250.dp.toPx())
                    )
                }) {
            Column {
                Text(
                    modifier = Modifier
                        .padding(top = 32.dp, start = 32.dp),
                    text = "Movies",
                    style = TextStyle(fontSize = 34.sp),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                nowPlayingMoviesHorizontalPager(
                    onClick = { navController.navigate(MoviesScreen.MovieDetailScreen.route + it) }
                )
                Text(
                    modifier = Modifier
                        .padding(start = 32.dp),
                    text = "Popular",
                    style = TextStyle(fontSize = 24.sp),
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                PopularMoviesScreen(navController = navController)
            }
        }
    }
}
/*    val gradient = Brush.linearGradient(
        0.3f to Color.Green,
        1.0f to RatingBarColor,
        start = Offset(0.0f, 50.0f),
        end = Offset(0.0f, 50.0f)
    )*/

    /*   @Composable
       fun gradiant() {
           Box(
               modifier = Modifier
                   .fillMaxSize()
                   .drawBehind {
                       drawRect(
                           brush = gradient,
                           topLeft = Offset(x = 0f, y = 0.dp.toPx()),
                           size = Size(500.dp.toPx(), 250.dp.toPx())
                       )
                   }


           )
       }*/


