package com.oguzhanturkmen.movee.common

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.oguzhanturkmen.movee.ui.theme.RatingBarColor

object Constants {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val BASE_BACKDROP_IMAGE_URL = "https://image.tmdb.org/t/p/w780/"
    const val BASE_POSTER_IMAGE_URL = "https://image.tmdb.org/t/p/w500/"
    const val API_KEY = "146006eead10e81276d447ee8c360b4e"
    const val PARAM_MOVIE_ID = "movieId"
    const val PARAM_TVSERIES_ID = "tvSeriesId"
    const val PARAM_PERSON_ID = "personId"
}

val gradient = Brush.linearGradient(
    0.3f to Color.Green,
    1.0f to RatingBarColor,
    start = Offset(0.0f, 50.0f),
    end = Offset(0.0f, 50.0f)
)
