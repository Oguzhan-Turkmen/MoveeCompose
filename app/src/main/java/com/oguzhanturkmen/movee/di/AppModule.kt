package com.oguzhanturkmen.movee.di


import android.app.Activity
import android.content.Context
import androidx.room.Room
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.oguzhanturkmen.movee.common.Constants.BASE_URL
import com.oguzhanturkmen.movee.data.local.MoveeDao
import com.oguzhanturkmen.movee.data.local.MoveeDb
import com.oguzhanturkmen.movee.data.mapper.*
import com.oguzhanturkmen.movee.data.remote.ApiService
import com.oguzhanturkmen.movee.data.repository.*
import com.oguzhanturkmen.movee.domain.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    fun provideFusedLocation() = LocationServices.getFusedLocationProviderClient(Activity())

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
        creditsDtoMapper: MovieCreditsDtoMapper,
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
        tvSeriesDetailDtoMapper: TvSeriesDetailDtoMapper,
        tvSeriesCreditsDtoMapper: TvSeriesCreditsDtoMapper
    ): TvSeriesRepository {
        return TvSeriesRepositoryImpl(
            api,
            tvSeriesDtoMapper,
            tvSeriesDetailDtoMapper,
            tvSeriesCreditsDtoMapper
        )
    }

    @Singleton
    @Provides
    fun provideSearchRepository(
        api: ApiService,
        multiSearchResultDtoMapper: MultiSearchResultDtoMapper
    ): SearchRepository {
        return SearchRepositoryImpl(api, multiSearchResultDtoMapper)
    }

    @Singleton
    @Provides
    fun provideMoveeDbRepository(
        moveeDao: MoveeDao
    ): MoveeDbRepository {
        return MoveeDbRepositoryImpl(moveeDao)
    }

    @Singleton
    @Provides
    fun provideFirebaseRepository(): FirebaseRepository {
        return FirebaseRepositoryImpl(provideFirebaseAuth())
    }

    @Provides
    fun provideFirebaseAuth() = Firebase.auth

    @Singleton
    @Provides
    fun provideMoveeDb(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        MoveeDb::class.java,
        "Movee_db"
    ).build()

    @Singleton
    @Provides
    fun provideMoveeDao(db: MoveeDb) = db.getDao()


}

