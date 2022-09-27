package com.oguzhanturkmen.movee.data.remote

import com.oguzhanturkmen.movee.common.Constants.API_KEY
import com.oguzhanturkmen.movee.data.remote.dto.movieDetailDto.MovieDetailDto
import com.oguzhanturkmen.movee.data.remote.dto.moviedto.MoviesDto
import com.oguzhanturkmen.movee.data.remote.dto.tvseriesdetaildto.TvSeriesDetailDto
import com.oguzhanturkmen.movee.data.remote.dto.tvseriesdto.TvSerialsDto
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

    @GET("tv/popular")
    suspend fun getPopularTvSerials(
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en"
    ): TvSerialsDto

    @GET("tv/top_rated")
    suspend fun getTopRatedTvSerials(
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en"
    ): TvSerialsDto

    @GET("tv/{tvSeriesId}")
    suspend fun getTvSeriesDetail(
        @Path("tvSeriesId") tvSeriesId: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): TvSeriesDetailDto

}