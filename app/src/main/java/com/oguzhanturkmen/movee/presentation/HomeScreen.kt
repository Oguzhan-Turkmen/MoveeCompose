package com.oguzhanturkmen.movee.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.oguzhanturkmen.movee.domain.model.Movie

@Composable
fun HomeScreen(movie: Movie, modifier: Modifier) {
    Text(
        text = "Now Playing",
        style = TextStyle(fontSize = 30.sp),
        color = Color.Black,
        fontWeight = FontWeight.Bold
    )
    Column() {
    }

}


@Preview
@Composable
fun previewHomeScreen() {
    HomeScreen(
        movie = Movie(
            1,
            "Joker",
            "asd",
            5.3,
            "2022-08-11"
        ),
        modifier = Modifier
    )
}