package com.oguzhanturkmen.movee.domain.repository

import com.oguzhanturkmen.movee.domain.model.credits.Cast
import com.oguzhanturkmen.movee.domain.model.movie.Movie
import com.oguzhanturkmen.movee.domain.model.movieDetail.MovieDetail


interface MovieRepository {
    suspend fun getPopularMovie(): List<Movie>
    suspend fun getNowPlayingMovie(): List<Movie>
    suspend fun getMovieDetail(movieId: Int): MovieDetail
    suspend fun getMovieCredits(movieId: Int): List<Cast>
}
