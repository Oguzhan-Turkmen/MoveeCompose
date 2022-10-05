package com.oguzhanturkmen.movee.presentation.search.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.oguzhanturkmen.movee.R
import com.oguzhanturkmen.movee.common.Constants
import com.oguzhanturkmen.movee.domain.model.search.MultiSearchResult
import com.oguzhanturkmen.movee.presentation.search.SearchViewModel
import com.oguzhanturkmen.movee.ui.theme.RatingBarColor
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage
import kotlinx.coroutines.delay
import java.util.*

@Composable
fun SearchBar(
    autoFocus: Boolean,
    viewModel: SearchViewModel = hiltViewModel(),
    onSearch: () -> Unit

) {
    Box(
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp, bottom = 8.dp)
            .clip(CircleShape)
            .fillMaxWidth()
            .height(54.dp)
            .background(Color.White)
    ) {
        var searchInput: String by remember { mutableStateOf("") }
        val focusRequester = remember { FocusRequester() }
        val focusManager = LocalFocusManager.current

        LaunchedEffect(key1 = searchInput) {
            if (viewModel.searchParam.value.trim().isNotEmpty() &&
                viewModel.searchParam.value.trim().length != viewModel.previousSearch.value.length &&
                viewModel.searchParam.value.trim().length >= 3
            ) {
                delay(500)
                onSearch()
                viewModel.previousSearch.value = searchInput.trim()
            }
        }

        TextField(
            value = searchInput,
            onValueChange = { newValue ->
                searchInput = if (newValue.trim().isNotEmpty()) newValue else ""
                viewModel.searchParam.value = searchInput
            },
            modifier = Modifier
                .fillMaxSize()
                .focusRequester(focusRequester = focusRequester)
                .background(Color.White),
            singleLine = true,
            placeholder = {
                Text(
                    text = "Search...",
                )
            },
            keyboardOptions = KeyboardOptions(
                autoCorrect = true,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    if (viewModel.searchParam.value.trim().isNotEmpty()) {
                        focusManager.clearFocus()
                        viewModel.searchParam.value = searchInput
                        if (searchInput != viewModel.previousSearch.value) {
                            viewModel.previousSearch.value = searchInput
                            onSearch()
                        }
                    }
                }
            ),
            trailingIcon = {
                LaunchedEffect(Unit) {
                    if (autoFocus) {
                        focusRequester.requestFocus()
                    }
                }
                Row {
                    AnimatedVisibility(visible = searchInput.trim().isNotEmpty()) {
                        IconButton(onClick = {
                            focusManager.clearFocus()
                            searchInput = ""
                            viewModel.searchParam.value = ""
                        }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = null
                            )
                        }
                    }
                    IconButton(onClick = {
                        if (viewModel.searchParam.value.trim().isNotEmpty()) {
                            focusManager.clearFocus()
                            viewModel.searchParam.value = searchInput
                            if (searchInput != viewModel.previousSearch.value) {
                                viewModel.previousSearch.value = searchInput
                                onSearch()
                            }
                        }
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_bottom_nav_icon_search),
                            contentDescription = null
                        )
                    }
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchResultItem(
    searchResult: MultiSearchResult,
    modifier: Modifier,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
        onClick = onClick,
        elevation = 10.dp
    )
    {
        Row(
            modifier = Modifier.padding(4.dp)
        ) {
            SearchImage(
                searchResult = searchResult,
                modifier = modifier
                    .padding(start = 8.dp, top = 8.dp)
            )
            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
            ) {
                SearchTitle(searchResult = searchResult)
                Spacer(modifier = Modifier.height(8.dp))
                SearchOverview(searchResult = searchResult)
                Spacer(modifier = Modifier.height(8.dp))
                SearchType(searchResult = searchResult)
            }
        }
    }

}

@Composable
fun SearchImage(
    searchResult: MultiSearchResult,
    modifier: Modifier
) {
    lateinit var posterPath: String
    if (searchResult.profilePath == null) {
        posterPath = Constants.BASE_BACKDROP_IMAGE_URL + searchResult.posterPath
    } else {
        posterPath = Constants.BASE_BACKDROP_IMAGE_URL + searchResult.profilePath
    }
    CoilImage(
        imageModel = posterPath,
        shimmerParams = ShimmerParams(
            baseColor = Color.White,
            highlightColor = RatingBarColor,
            durationMillis = 500,
            dropOff = 0.65F,
            tilt = 20F,

            ),
        modifier = Modifier
            .size(70.dp, 100.dp)
            .clip(RoundedCornerShape(10.dp)),
        failure = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.image_not_available),
                    contentDescription = "no image"
                )
            }
        },
        previewPlaceholder = R.drawable.image_not_available,
        contentScale = ContentScale.Crop,
        circularReveal = CircularReveal(duration = 1000),
    )
}

@Composable
fun SearchTitle(
    searchResult: MultiSearchResult,
) {
    if (searchResult.name == null) {
        Text(
            text = "${searchResult.originalTitle}",
            style = TextStyle(fontSize = 20.sp),
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )
    } else {
        Text(
            text = "${searchResult.name}",
            style = TextStyle(fontSize = 20.sp),
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )
    }
}

@Composable
fun SearchType(searchResult: MultiSearchResult) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (searchResult.mediaType == "movie") {
            Image(
                modifier = Modifier,
                painter = painterResource(id = R.drawable.ic_movie_icon),
                contentDescription = null,
            )
        } else if (searchResult.mediaType == "tv") {
            Image(
                modifier = Modifier,
                painter = painterResource(id = R.drawable.ic_tv_icon),
                contentDescription = null,
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.ic_person_icon),
                contentDescription = null,
            )
        }
        Text(
            modifier = Modifier
                .padding(start = 4.dp)
                .padding(vertical = 4.dp),
            text = "${searchResult.mediaType}".capitalized(),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

fun String.capitalized(): String {
    return this.replaceFirstChar {
        if (it.isLowerCase())
            it.titlecase(Locale.getDefault())
        else it.toString()
    }
}

@Composable
fun SearchOverview(searchResult: MultiSearchResult) {
    if (searchResult.overview != null) {
        Text(
            text = "${searchResult.overview}",
            style = TextStyle(fontSize = 16.sp),
            color = Color.Black,
            maxLines = 2
        )
    } else {
        Text(
            text = "${searchResult.knownForDepartment}",
            style = TextStyle(fontSize = 16.sp),
            color = Color.Black,
            maxLines = 2
        )
    }

}