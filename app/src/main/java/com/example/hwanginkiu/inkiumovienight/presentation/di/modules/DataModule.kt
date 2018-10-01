package com.example.hwanginkiu.inkiumovienight.presentation.di.modules

import android.arch.persistence.room.Room
import android.content.Context
import com.example.hwanginkiu.inkiumovienight.data.api.Api
import com.example.hwanginkiu.inkiumovienight.data.db.FavoriteMovieDBCache
import com.example.hwanginkiu.inkiumovienight.data.db.MoviesDatabase
import com.example.hwanginkiu.inkiumovienight.data.mappers.MovieDataMovieEntityMapper
import com.example.hwanginkiu.inkiumovienight.data.mappers.MovieEntityMovieDataMapper
import com.example.hwanginkiu.inkiumovienight.data.repositries.MoviesRepositoryImpl
import com.example.hwanginkiu.inkiumovienight.data.repositries.RemoteMoviesDataStore
import com.example.hwanginkiu.inkiumovienight.domain.MoviesCache
import com.example.hwanginkiu.inkiumovienight.domain.MoviesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
@Singleton
class DataModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(context: Context): MoviesDatabase {
        return Room.databaseBuilder(
                context,
                MoviesDatabase::class.java,
                "movies_db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideMovieRepository(api: Api): MoviesRepository {
        return MoviesRepositoryImpl(RemoteMoviesDataStore(api))
    }

    @Singleton
    @Provides
    fun provideFavoriteMovieCache(moviesDatabase: MoviesDatabase,
                                  entityMovieDataMapper: MovieEntityMovieDataMapper,
                                  movieDataMovieEntityMapper: MovieDataMovieEntityMapper): MoviesCache {
        return FavoriteMovieDBCache(moviesDatabase, entityMovieDataMapper, movieDataMovieEntityMapper)
    }
}