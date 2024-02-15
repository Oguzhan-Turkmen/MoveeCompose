package com.oguzhanturkmen.movee.presentation.topratedtvseries.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oguzhanturkmen.movee.R
import com.oguzhanturkmen.movee.common.Constants.BASE_BACKDROP_IMAGE_URL
import com.oguzhanturkmen.movee.common.withDecimalDigits
import com.oguzhanturkmen.movee.domain.model.series.TvSeries
import com.oguzhanturkmen.movee.presentation.theme.RatingBarColor
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun topRatedTvSeriesImage(
    tvSeries: TvSeries,
    modifier: Modifier
) {
    val posterPath = BASE_BACKDROP_IMAGE_URL + tvSeries.posterPath
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
            .size(80.dp, 110.dp)
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
fun topRatedTvSeriesRating(
    tvSeries: TvSeries,
    modifier: Modifier
) {
    val shape = RoundedCornerShape(12.dp)
    Box(
        modifier = Modifier
            .size(60.dp, 24.dp)
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
                text = "${tvSeries.voteAvarage.withDecimalDigits(1)}",
                fontSize = 10.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun topRatedTvSeriesTitle(
    tvSeries: TvSeries,
) {
    Text(
        modifier = Modifier,
        text = "${tvSeries.name}",
        style = TextStyle(fontSize = 20.sp),
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
    )
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun topRatedTvSeriesItem(
    tvSeries: TvSeries,
    modifier: Modifier,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .size(200.dp, 200.dp)
            .padding(start = 16.dp, end = 16.dp),
        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
        onClick = onClick,
        elevation = 10.dp
    )
    {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            topRatedTvSeriesImage(
                tvSeries = tvSeries,
                modifier = modifier
            )
            topRatedTvSeriesTitle(tvSeries = tvSeries)
            Spacer(modifier = Modifier.height(8.dp))
            topRatedTvSeriesRating(tvSeries = tvSeries, modifier = modifier)
        }
    }
}

@Preview
@Composable
fun preview() {
    topRatedTvSeriesItem(
        tvSeries = TvSeries(
            1,
            "Fight Club",
            5.3,
            "/z0iCS5Znx7TeRwlYSd4c01Z0lFx.jpg",
            "2018-07-08"
        ),
        modifier = Modifier
    ) {

    }
}