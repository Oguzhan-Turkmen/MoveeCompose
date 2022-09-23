package com.oguzhanturkmen.movee.presentation.movieDetail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.oguzhanturkmen.movee.common.Constants
import com.oguzhanturkmen.movee.domain.model.movieDetail.MovieDetail
import com.oguzhanturkmen.movee.ui.theme.RatingBarColor
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun movieDetailImage(
    movieDetail: MovieDetail
) {
    val posterPath = Constants.BASE_BACKDROP_IMAGE_URL + movieDetail.backdropPath
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
            .fillMaxWidth()
            .fillMaxHeight(),

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
fun movieDetailRating(movieDetail: MovieDetail) {
    val shape = RoundedCornerShape(12.dp)
    Box(
        modifier = Modifier
            .size(66.dp, 28.dp)
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
                    .size(16.dp, 16.dp)
                    .padding(1.dp)
            )
            Text(
                text = "%.1f".format(movieDetail.voteAverage),
                fontSize = 14.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun movieDetailTitle(movieDetail: MovieDetail) {
    Text(
        text = "${movieDetail.originalTitle}",
        style = TextStyle(fontSize = 28.sp),
        color = Color.Black,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun movieDetailRunTime(movieDetail: MovieDetail) {
    Row(
        verticalAlignment = Alignment.CenterVertically

    ) {
        Image(
            modifier = Modifier.size(24.dp, 24.dp),
            painter = painterResource(id = R.drawable.clock),
            contentDescription = null,
        )
        Text(
            modifier = Modifier
                .padding(start = 4.dp)
                .padding(vertical = 4.dp),
            text = "${movieDetail.runtime} min",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun movieDetailReleaseDate(movieDetail: MovieDetail) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(24.dp, 24.dp),
            painter = painterResource(id = R.drawable.calendar),
            contentDescription = null,
        )
        Text(
            modifier = Modifier
                .padding(start = 4.dp)
                .padding(vertical = 4.dp),
            text = "${movieDetail.releaseDate}",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun movieDetailOverview(movieDetail: MovieDetail) {
    Text(
        text = "${movieDetail.overview}",
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(top = 8.dp)
    )
}

