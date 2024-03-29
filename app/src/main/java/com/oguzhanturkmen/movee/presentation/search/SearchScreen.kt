package com.oguzhanturkmen.movee.presentation.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.oguzhanturkmen.movee.common.gradient
import com.oguzhanturkmen.movee.presentation.navigation.MainScreens
import com.oguzhanturkmen.movee.presentation.search.components.SearchBar
import com.oguzhanturkmen.movee.presentation.search.components.SearchResultItem

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navController: NavController,
) {
    val state = viewModel.state.value
    Box(modifier =
    Modifier.drawBehind {
        drawRect(
            brush = gradient,
            topLeft = Offset(x = 0f, y = 0.dp.toPx()),
            size = Size(500.dp.toPx(), 250.dp.toPx())
        )
    }) {
        Column(verticalArrangement = Arrangement.spacedBy(56.dp)) {
            Text(
                modifier = Modifier
                    .padding(top = 32.dp, start = 32.dp),
                text = "Search",
                style = TextStyle(fontSize = 34.sp),
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            SearchBar(
                autoFocus = true,
                onSearch = {
                    viewModel.getSearchResults()
                }
            )
            LazyColumn(
                Modifier.padding(start = 4.dp, end = 4.dp),
                contentPadding = PaddingValues(vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(state.searchResult) { searchResult ->
                    SearchResultItem(
                        searchResult = searchResult,
                        modifier = Modifier,
                        onClick = {
                            if (searchResult.mediaType == "movie") {
                                navController.navigate(MainScreens.MainMovieDetailScreen.route + "${searchResult.id}")
                            } else if (searchResult.mediaType == "tv") {
                                navController.navigate(MainScreens.MainTvSeriesDetailScreen.route + "${searchResult.id}")
                            } else {
                                navController.navigate(MainScreens.MainPersonDetailScreen.route + "${searchResult.id}")
                            }
                        }
                    )
                }
            }
        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}


