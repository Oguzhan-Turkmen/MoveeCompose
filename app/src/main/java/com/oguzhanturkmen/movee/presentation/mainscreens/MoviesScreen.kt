package com.oguzhanturkmen.movee.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.oguzhanturkmen.movee.R
import com.oguzhanturkmen.movee.common.gradient
import com.oguzhanturkmen.movee.presentation.navigation.MainScreens
import com.oguzhanturkmen.movee.presentation.nowPlayingMovie.nowPlayingMoviesHorizontalPager
import com.oguzhanturkmen.movee.presentation.popularMovie.PopularMoviesViewModel
import com.oguzhanturkmen.movee.presentation.popularMovie.components.PopularMoviesItem

@Composable
fun MoviesScreen(
    navController: NavController,
    modifier: Modifier = Modifier.padding(top = 12.dp),
    viewModel: PopularMoviesViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
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
                Row(
                    modifier = Modifier.padding(top = 32.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(120.dp)

                ) {
                    Text(
                        modifier = Modifier
                            .padding(start = 32.dp),
                        text = "Movies",
                        style = TextStyle(fontSize = 34.sp),
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    mapIcon(
                        onClick = {
                            navController.navigate(MainScreens.MainMapScreen.route)
                        }
                    )
                }
                nowPlayingMoviesHorizontalPager(
                    onClick = {
                        navController.navigate(MainScreens.MainMovieDetailScreen.route + it)
                    })
            }
        }
        item {
            Text(
                modifier = Modifier
                    .padding(start = 32.dp),
                text = "Popular",
                style = TextStyle(fontSize = 24.sp),
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
        items(state.popularMovies) { movie ->
            PopularMoviesItem(
                movie = movie,
                modifier = Modifier,
                onClick = {
                    navController.navigate(MainScreens.MainMovieDetailScreen.route + "${movie.id}")
                }
            )
        }
    }
}

@Composable
fun mapIcon(
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() })
            { onClick() },
    ) {
        Box(
            modifier = Modifier
                .size(30.dp, 30.dp)
                .clip(CircleShape)
                .background(Color.White),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.map_icon),
                contentDescription = "map_icon",
                modifier = Modifier.size(14.dp, 19.dp)
            )
        }
    }
}





