package com.oguzhanturkmen.movee.presentation.tvseries.populartvserial.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.oguzhanturkmen.movee.common.withDecimalDigits
import com.oguzhanturkmen.movee.domain.model.series.TvSeries
import com.oguzhanturkmen.movee.presentation.theme.RatingBarColor
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun popularTvSeriesImage(
    tvSeries: TvSeries,
    modifier: Modifier
) {
    val posterPath = Constants.BASE_BACKDROP_IMAGE_URL + tvSeries.posterPath
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
            .size(200.dp, 300.dp)
            .clip(RoundedCornerShape(12.dp)),
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
fun popularTvSeriesRating(tvSeries: TvSeries, modifier: Modifier) {
    val shape = RoundedCornerShape(12.dp)
    Box(
        modifier = Modifier
            .size(61.dp, 28.dp)
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
                    .size(20.dp, 20.dp)
                    .padding(1.dp)
            )
            Text(
                text = "${tvSeries.voteAvarage.withDecimalDigits(1)}",
                fontSize = 14.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun popularTvSeriesTitle(tvSeries: TvSeries, modifier: Modifier) {
    Text(
        text = "${tvSeries.name}",
        style = TextStyle(fontSize = 20.sp),
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        maxLines = 1

    )
}
