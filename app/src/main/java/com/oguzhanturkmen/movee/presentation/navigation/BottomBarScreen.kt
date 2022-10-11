package com.oguzhanturkmen.movee.presentation.navigation

import androidx.annotation.DrawableRes
import com.oguzhanturkmen.movee.R


sealed class BottomBarScreen(
    val route: String,
    @DrawableRes
    val image: Int
) {
    object Movies : BottomBarScreen(
        route = "main_movie_screen",
        image = R.drawable.ic_bottom_nav_icon_movies
    )

    object Series : BottomBarScreen(
        route = "main_tv_series_screen",
        image = R.drawable.ic_bottom_nav_icon_series
    )

    object Search : BottomBarScreen(
        route = "search_home_screen",
        image = R.drawable.ic_bottom_nav_icon_search
    )

    object Profile : BottomBarScreen(
        route = "main_profile_screen",
        image = R.drawable.ic_icons_tabbar_profil_selected
    )

}
