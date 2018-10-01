package com.example.hwanginkiu.inkiumovienight.data.repositries

import android.util.Log
import com.example.hwanginkiu.inkiumovienight.data.api.Api
import com.example.hwanginkiu.inkiumovienight.data.mappers.DetailsDataMovieEntityMapper
import com.example.hwanginkiu.inkiumovienight.domain.MoviesDataStore
import com.example.hwanginkiu.inkiumovienight.domain.entities.MovieEntity
import com.example.hwanginkiu.inkiumovienight.domain.entities.Optional
import com.example.hwanginkiu.inkiumovienight.data.mappers.MovieDataMovieEntityMapper
import io.reactivex.Observable

class RemoteMoviesDataStore(private val api: Api) : MoviesDataStore {

    private val movieDataMovieEntityMapper = MovieDataMovieEntityMapper()
    private val detailsDataMovieEntityMapper = DetailsDataMovieEntityMapper()

    override fun getMovieById(movieId: Int): Observable<Optional<MovieEntity>> {
        return api.getMovieDetails(movieId).flatMap {
            Observable.just(Optional.of(detailsDataMovieEntityMapper.mapFrom(it)))
        }
    }

    override fun getMovies(): Observable<List<MovieEntity>> {
        return api.getPopularMovies().map { results ->
            results.movies.map { movieDataMovieEntityMapper.mapFrom(it) }
        }
    }

    override fun search(query: String): Observable<List<MovieEntity>> {
        return api.searchMovies(query).map { results ->
            results.movies.map { movieDataMovieEntityMapper.mapFrom(it) }
        }
    }
}