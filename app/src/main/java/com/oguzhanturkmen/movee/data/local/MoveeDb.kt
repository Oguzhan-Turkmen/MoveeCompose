package com.oguzhanturkmen.movee.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.oguzhanturkmen.movee.domain.model.movie.Movie

@Database(
    entities = [Movie::class],
    version = 1
)
abstract class MoveeDb : RoomDatabase() {

    abstract fun getDao(): MoveeDao
}