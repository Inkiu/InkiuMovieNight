package com.example.hwanginkiu.inkiumovienight.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.hwanginkiu.inkiumovienight.data.entities.MovieData

@Database(entities = [MovieData::class], version = 1)
abstract class MoviesDatabase: RoomDatabase() {

    abstract fun getMoviesDao(): MoviesDao

}