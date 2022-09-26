package com.oguzhanturkmen.movee.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Tv
import androidx.compose.ui.graphics.vector.ImageVector


sealed class BottomBarScreen(
    val route: String,
    val image: ImageVector
) {
    object Movies : BottomBarScreen(
        route = "Movies",
        image = Icons.Default.Movie
    )

    object Series : BottomBarScreen(
        route = "Series",
        image = Icons.Default.Tv
    )
}
