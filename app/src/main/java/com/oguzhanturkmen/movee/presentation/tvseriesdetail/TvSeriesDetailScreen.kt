package com.oguzhanturkmen.movee.presentation.tvseriesdetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.oguzhanturkmen.movee.presentation.tvseriesdetail.components.*

@Composable
fun TvSeriesDetailScreen(
    viewModel: TvSeriesDetailViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 64.dp)
    ) {
        state.tvSeries?.let { tvSeriesDetail ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                tvSeriesDetailImage(tvSeriesDetail = tvSeriesDetail)
                Column(
                    Modifier.padding(start = 32.dp, top = 8.dp, end = 32.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    tvSeriesDetailRating(tvSeriesDetail = tvSeriesDetail)
                    tvSeriesDetailTitle(tvSeriesDetail = tvSeriesDetail)
                    Row(horizontalArrangement = Arrangement.spacedBy(32.dp)) {
                        tvSeriesDetilFirstAirDate(tvSeriesDetail = tvSeriesDetail)
                        tvSeriesDetailEpisodeAndSeasonNumber(tvSeriesDetail = tvSeriesDetail)
                    }
                    tvSeriesDetailOverview(tvSeriesDetail = tvSeriesDetail)
                }
            }
        }
    }
}