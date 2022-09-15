package com.oguzhanturkmen.movee.domain.repository

import com.oguzhanturkmen.movee.data.remote.dto.MoviesDto
import dagger.Provides


interface MovieRepository {

    suspend fun getPopularMovie(): List<MoviesDto>
}