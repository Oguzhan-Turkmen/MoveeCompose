package com.oguzhanturkmen.movee.presentation.navigation

import androidx.annotation.DrawableRes
import com.oguzhanturkmen.movee.R


sealed class BottomBarScreen(
    val route: String,
    @DrawableRes
    val image: Int
) {
    object Movies : BottomBarScreen(
        route = "Movies",
        image = R.drawable.ic_bottom_nav_icon_movies
    )

    object Series : BottomBarScreen(
        route = "Series",
        image = R.drawable.ic_bottom_nav_icon_series
    )
}
