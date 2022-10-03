package com.oguzhanturkmen.movee.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.oguzhanturkmen.movee.presentation.navigation.TvSeriesScreen
import com.oguzhanturkmen.movee.presentation.topratedtvseries.TopRatedTvSeriesViewModel
import com.oguzhanturkmen.movee.presentation.topratedtvseries.components.topRatedTvSeriesItem
import com.oguzhanturkmen.movee.presentation.tvseries.populartvserial.popularTvSerialsHorizontalPager

//@Composable
//fun TvSeriesScreen(
//    navController: NavController
//) {
//    val scaffoldState = rememberScaffoldState()
//    Scaffold(scaffoldState = scaffoldState) {
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .drawBehind {
//                    drawRect(
//                        brush = gradient,
//                        topLeft = Offset(x = 0f, y = 0.dp.toPx()),
//                        size = Size(500.dp.toPx(), 250.dp.toPx())
//                    )
//                }) {
//            Column(
//                //modifier = Modifier.verticalScroll(rememberScrollState())
//            ) {
//                Text(
//                    modifier = Modifier
//                        .padding(top = 32.dp, start = 32.dp),
//                    text = "Tv Series",
//                    style = TextStyle(fontSize = 34.sp),
//                    color = Color.White,
//                    fontWeight = FontWeight.Bold
//                )
//                popularTvSerialsHorizontalPager(
//                    onClick = { navController.navigate(TvSeriesScreen.TvSeriesDetailScreen.route + it) }
//                )
//                Text(
//                    modifier = Modifier
//                        .padding(start = 32.dp),
//                    text = "Top Rated",
//                    style = TextStyle(fontSize = 24.sp),
//                    color = Color.Black,
//                    fontWeight = FontWeight.Bold
//                )
//                TopRatedTvSeriesScreen(navController = navController)
//            }
//        }
//    }
//}

@Composable
fun TvSeriesScreen(
    navController: NavController,
    modifier: Modifier = Modifier.padding(top = 12.dp),
    viewModel: TopRatedTvSeriesViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item {
            Column(modifier = Modifier
                .fillMaxWidth()
                .drawBehind {
                    drawRect(
                        brush = gradient,
                        topLeft = Offset(x = 0f, y = 0.dp.toPx()),
                        size = Size(500.dp.toPx(), 250.dp.toPx())
                    )
                }) {
                Text(
                    modifier = Modifier
                        .padding(top = 32.dp, start = 32.dp),
                    text = "Popular",
                    style = TextStyle(fontSize = 34.sp),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                popularTvSerialsHorizontalPager(
                    onClick = {
                        navController.navigate(TvSeriesScreen.TvSeriesDetailScreen.route + it)
                    })
            }
        }
        item {
            Text(
                modifier = Modifier
                    .padding(start = 32.dp),
                text = "Top Rated",
                style = TextStyle(fontSize = 24.sp),
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
        items(state.topRatedTvSerials.windowed(2, 2, true)) { tvserial ->
            Row(Modifier.fillMaxWidth()) {
                tvserial.forEach { tvseries ->
                    topRatedTvSeriesItem(
                        tvSeries = tvseries,
                        modifier = Modifier,
                        onClick = {
                            navController.navigate(TvSeriesScreen.TvSeriesDetailScreen.route + "${tvseries.id}")
                        }
                    )
                }
            }
        }
    }
}

