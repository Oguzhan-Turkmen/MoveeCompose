package com.oguzhanturkmen.movee.presentation.tvseries.populartvserial

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.oguzhanturkmen.movee.presentation.tvseries.populartvserial.components.popularTvSeriesImage
import com.oguzhanturkmen.movee.presentation.tvseries.populartvserial.components.popularTvSeriesRating
import com.oguzhanturkmen.movee.presentation.tvseries.populartvserial.components.popularTvSeriesTitle
import kotlin.math.absoluteValue


@OptIn(ExperimentalPagerApi::class)
@Composable
fun popularTvSerialsHorizontalPager(
    modifier: Modifier = Modifier.padding(top = 12.dp),
    viewmodel: PopularTvSeriesViewModel = hiltViewModel(),
    onClick: (movieId: Int) -> Unit
) {
    val state = viewmodel.state.value
    HorizontalPager(
        count = viewmodel.state.value.popularTvSerials.size,
        contentPadding = PaddingValues(horizontal = 20.dp),
        modifier = modifier,
    ) { page ->
        Column(
            Modifier
                .graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                    lerp(
                        start = 0.90f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 0f)
                    )
                }
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }) {
                    onClick(state.popularTvSerials[page].id)
                }
                .fillMaxWidth()
                .aspectRatio(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            //verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            popularTvSeriesImage(
                tvSeries = state.popularTvSerials[page],
                modifier = Modifier.padding(start = 4.dp, top = 4.dp)
            )
            popularTvSeriesTitle(tvSeries = state.popularTvSerials[page], modifier = Modifier)
            popularTvSeriesRating(tvSeries = state.popularTvSerials[page], modifier = Modifier)
        }
    }
    if (state.error.isNotBlank()) {
        Text(
            text = state.error,
            color = MaterialTheme.colors.error,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)

        )
    }
    if (state.isLoading) {
        CircularProgressIndicator(modifier = Modifier)
    }
}