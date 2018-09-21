package com.example.hwanginkiu.inkiumovienight.data.db

import com.example.hwanginkiu.inkiumovienight.data.mappers.MovieDataMovieEntityMapper
import com.example.hwanginkiu.inkiumovienight.data.mappers.MovieEntityMovieDataMapper
import com.example.hwanginkiu.inkiumovienight.domain.MoviesCache
import com.example.hwanginkiu.inkiumovienight.domain.entities.MovieEntity
import com.example.hwanginkiu.inkiumovienight.domain.entities.Optional
import io.reactivex.Observable

class FavoriteMovieDBCache(database: MoviesDatabase,
                           private val entityToDataMapper: MovieEntityMovieDataMapper,
                           private val dataToEntityMapper: MovieDataMovieEntityMapper
) : MoviesCache {

    private val dao: MoviesDao = database.getMoviesDao()
    override fun clear() {
        dao.clear()
    }

    override fun save(movieEntity: MovieEntity) {
        dao.saveMovie(entityToDataMapper.mapFrom(movieEntity))
    }

    override fun remove(movieEntity: MovieEntity) {
        dao.removeMovie(entityToDataMapper.mapFrom(movieEntity))
    }

    override fun saveAll(movieEntities: List<MovieEntity>) {
        dao.saveAllMovies(movieEntities.map { entityToDataMapper.mapFrom(it) })
    }

    override fun getAll(): Observable<List<MovieEntity>> {
        return Observable.fromCallable { dao.getFavorites().map { dataToEntityMapper.mapFrom(it) } }
    }

    override fun get(movieId: Int): Observable<Optional<MovieEntity>> {
        return Observable.fromCallable {
            dao.get(movieId)?.let {
                Optional.of(dataToEntityMapper.mapFrom(it))
            } ?: Optional.empty()
        }
    }

    override fun search(query: String): Observable<List<MovieEntity>> {
        return Observable.fromCallable {
            dao.search("%$query%").map { dataToEntityMapper.mapFrom(it) }
        }
    }

    override fun isEmpty(): Observable<Boolean> {
        return Observable.fromCallable {
            dao.getFavorites().isEmpty()
        }
    }
}