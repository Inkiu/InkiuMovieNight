package com.example.hwanginkiu.inkiumovienight.data.repositries

import com.example.hwanginkiu.inkiumovienight.domain.MoviesRepository
import com.example.hwanginkiu.inkiumovienight.domain.entities.MovieEntity
import com.example.hwanginkiu.inkiumovienight.domain.entities.Optional
import io.reactivex.Observable

class MoviesRepositoryImpl(
        private val remoteMoviesDataStore: RemoteMoviesDataStore
) : MoviesRepository {

    override fun getMovies(): Observable<List<MovieEntity>> {
        return remoteMoviesDataStore.getMovies()
    }

    override fun search(query: String): Observable<List<MovieEntity>> {
        return remoteMoviesDataStore.search(query)
    }

    override fun getMovie(movieId: Int): Observable<Optional<MovieEntity>> {
        return remoteMoviesDataStore.getMovieById(movieId)
    }

}