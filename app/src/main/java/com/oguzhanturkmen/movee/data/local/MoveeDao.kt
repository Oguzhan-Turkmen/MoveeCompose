package com.oguzhanturkmen.movee.data.local

import androidx.room.*
import com.oguzhanturkmen.movee.domain.model.movie.Movie

@Dao
interface MoveeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(movie: Movie): Long

    @Delete
    suspend fun deleteMovie(movie: Movie)

    @Query("Select * FROM movies")
    suspend fun getAllMovies(): List<Movie>

}