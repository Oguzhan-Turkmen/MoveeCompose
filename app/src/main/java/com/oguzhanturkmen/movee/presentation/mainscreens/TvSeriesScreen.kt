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
import com.oguzhanturkmen.movee.presentation.navigation.TvSeriesScreen
import com.oguzhanturkmen.movee.presentation.nowPlayingMovie.components.gradient
import com.oguzhanturkmen.movee.presentation.topratedtvseries.TopRatedTvSeriesScreen
import com.oguzhanturkmen.movee.presentation.tvseries.populartvserial.popularTvSerialsHorizontalPager


@Composable
fun TvSeriesScreen(
    navController: NavController
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
            Column(
                //modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 32.dp, start = 32.dp),
                    text = "Tv Series",
                    style = TextStyle(fontSize = 34.sp),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                popularTvSerialsHorizontalPager(
                    onClick = { navController.navigate(TvSeriesScreen.TvSeriesDetailScreen.route + it) }
                )
                Text(
                    modifier = Modifier
                        .padding(start = 32.dp),
                    text = "Top Rated",
                    style = TextStyle(fontSize = 24.sp),
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                TopRatedTvSeriesScreen(navController = navController)
            }
        }
    }
}