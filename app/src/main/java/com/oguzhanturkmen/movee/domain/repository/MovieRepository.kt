package com.oguzhanturkmen.movee.domain.repository

import com.oguzhanturkmen.movee.domain.model.credits.MovieCast
import com.oguzhanturkmen.movee.domain.model.movie.Movie
import com.oguzhanturkmen.movee.domain.model.movieDetail.MovieDetail
import com.oguzhanturkmen.movee.domain.model.person.Person
import com.oguzhanturkmen.movee.domain.model.personcredits.PersonCredits


interface MovieRepository {
    suspend fun getPopularMovie(): List<Movie>
    suspend fun getNowPlayingMovie(): List<Movie>
    suspend fun getMovieDetail(movieId: Int): MovieDetail
    suspend fun getMovieCredits(movieId: Int): List<MovieCast>
    suspend fun getPersonDetail(personId: Int): Person
    suspend fun getPersonCredits(personId: Int): List<PersonCredits>
}
