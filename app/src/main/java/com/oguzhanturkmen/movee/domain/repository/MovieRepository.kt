package com.oguzhanturkmen.movee.domain.repository

import com.oguzhanturkmen.movee.domain.model.Movie


interface MovieRepository {
    suspend fun getPopularMovie(): List<Movie>
    suspend fun getNowPlayingMovie(): List<Movie>
}