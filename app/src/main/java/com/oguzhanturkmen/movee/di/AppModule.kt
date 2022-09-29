package com.oguzhanturkmen.movee.di

import com.oguzhanturkmen.movee.common.Constants.BASE_URL
import com.oguzhanturkmen.movee.data.mapper.*
import com.oguzhanturkmen.movee.data.remote.ApiService
import com.oguzhanturkmen.movee.data.repository.MovieRepositoryImpl
import com.oguzhanturkmen.movee.data.repository.TvSeriesRepositoryImpl
import com.oguzhanturkmen.movee.domain.repository.MovieRepository
import com.oguzhanturkmen.movee.domain.repository.TvSeriesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun providesAPIService(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideMovieRepository(
        api: ApiService,
        movieDtoMapper: MovieDtoMapper,
        movieDetailDtoMapper: MovieDetailDtoMapper,
        creditsDtoMapper: CreditsDtoMapper,
        personDtoMapper: PersonDtoMapper,
        personCreditsDtoMapper: PersonCreditsDtoMapper,
    ): MovieRepository {
        return MovieRepositoryImpl(
            api,
            movieDtoMapper,
            movieDetailDtoMapper,
            creditsDtoMapper,
            personDtoMapper,
            personCreditsDtoMapper
        )
    }

    @Singleton
    @Provides
    fun provideTvSeriesRepository(
        api: ApiService,
        tvSeriesDtoMapper: TvSeriesDtoMapper,
        tvSeriesDetailDtoMapper: TvSeriesDetailDtoMapper
    ): TvSeriesRepository {
        return TvSeriesRepositoryImpl(api, tvSeriesDtoMapper, tvSeriesDetailDtoMapper)
    }
}

