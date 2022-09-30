package com.oguzhanturkmen.movee.presentation.popularMovie.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oguzhanturkmen.movee.R
import com.oguzhanturkmen.movee.common.Constants.BASE_BACKDROP_IMAGE_URL
import com.oguzhanturkmen.movee.domain.model.movie.Movie
import com.oguzhanturkmen.movee.ui.theme.RatingBarColor
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun PopularMovieImage(
    movie: Movie,
    modifier: Modifier
) {
    val posterPath = BASE_BACKDROP_IMAGE_URL + movie.posterPath
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
fun popularMovieRating(movie: Movie, modifier: Modifier) {
    val shape = RoundedCornerShape(12.dp)
    Box(
        modifier = Modifier
            .size(50.dp, 20.dp)
            .clip(shape)
            .background(RatingBarColor),
        Alignment.Center
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        )
        {
            Alignment.Center
            Image(
                painter = painterResource(id = R.drawable.star),
                contentDescription = null,
                modifier = Modifier
                    .size(13.dp, 13.dp)
                    .padding(1.dp)
            )
            Text(
                text = "${movie.voteAvarage}",
                fontSize = 10.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun PopularMovieTitle(movie: Movie, modifier: Modifier) {
    Text(
        text = "${movie.originalTitle}",
        style = TextStyle(fontSize = 20.sp),
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        maxLines = 1
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PopularMoviesItem(
    movie: Movie,
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
            PopularMovieImage(
                movie = movie,
                modifier = modifier
                    .padding(start = 8.dp, top = 8.dp)
            )
            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
            ) {
                PopularMovieTitle(
                    movie = movie,
                    modifier = modifier
                )
                Spacer(modifier = Modifier.height(8.dp))
                popularMovieRating(movie = movie, modifier = modifier)
                Spacer(modifier = Modifier.height(8.dp))
                showReleaseDate(movie = movie, modifier = modifier)
            }
        }
    }
}


@Composable
fun showReleaseDate(movie: Movie, modifier: Modifier) {
    Row(
    ) {
        Image(
            modifier = Modifier,
            painter = painterResource(id = R.drawable.calendar),
            contentDescription = null,
        )
        Text(
            modifier = Modifier
                .padding(start = 4.dp)
                .padding(vertical = 4.dp),
            text = "${movie.releaseDate}",
            fontSize = 12.sp
        )
    }
}

/*
@Preview
@Composable
fun previewReleaseDate() {
    showRealeseDate(
        movie =
        Movie(
            1,
            "Joker",
            "asd",
            5.3,
            "2022-08-11"
        ),
        modifier = Modifier
    )
}
*/

// Bir sürü paramatre eklendi preview yaparkan hata alabilirsin parametreleri kontrol et açmadan önce

/*
@Preview
@Composable
fun preview() {
    PopularMoviesItem(
        movie = Movie(
            1,
            "Joker",
            "asd",
            5.3,
            "2022-08-11"
        ), modifier = Modifier
    )
}


@Preview
@Composable
fun PreviewPopularMovieTitle() {
    PopularMovieTitle(movie = Movie(1, "Joker", "asd", 5.3), modifier = Modifier)
}

@Preview
@Composable
fun PreviewRating() {
    Rating(movie = Movie(1, "Joker", "asd", 5.3), modifier = Modifier)
}

@Preview
@Composable
fun PreviewImage() {
    PopularMovieImage(
        movie = Movie(
            1,
            "Joker",
            "/v28T5F1IygM8vXWZIycfNEm3xcL.jpg",
            5.4
        ),
        modifier = Modifier
    )
}
*/

