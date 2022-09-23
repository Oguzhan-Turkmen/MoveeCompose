package com.oguzhanturkmen.movee.data.remote

import com.oguzhanturkmen.movee.common.Constants.API_KEY
import com.oguzhanturkmen.movee.data.remote.dto.MoviesDto
import com.oguzhanturkmen.movee.data.remote.dto.movieDetailDto.MovieDetailDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en"
    ): MoviesDto

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en"
    ): MoviesDto

    @GET("movie/{movieId}")
    suspend fun getMovieDetail(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
    ): MovieDetailDto

}