package com.oguzhanturkmen.movee.di

import com.oguzhanturkmen.movee.common.Constants.BASE_URL
import com.oguzhanturkmen.movee.data.mapper.MovieDetailDtoMapper
import com.oguzhanturkmen.movee.data.mapper.MovieDtoMapper
import com.oguzhanturkmen.movee.data.remote.ApiService
import com.oguzhanturkmen.movee.data.repository.MovieRepositoryImpl
import com.oguzhanturkmen.movee.domain.repository.MovieRepository
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
        movieDetailDtoMapper: MovieDetailDtoMapper
    ): MovieRepository {
        return MovieRepositoryImpl(api, movieDtoMapper, movieDetailDtoMapper)
    }
}
/*
    @Singleton
    private val provideMoshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}*/
