package com.example.hwanginkiu.inkiumovienight.domain.usecases

import com.example.hwanginkiu.inkiumovienight.domain.MoviesCache
import com.example.hwanginkiu.inkiumovienight.domain.common.Transformer
import com.example.hwanginkiu.inkiumovienight.domain.entities.MovieEntity
import io.reactivex.Observable
import java.lang.IllegalArgumentException

class SaveFavoriteMovie(
        transformer: Transformer<Boolean>,
        private val moviesCache: MoviesCache
) : UseCase<Boolean>(transformer) {

    companion object {
        private const val PARAM_MOVIE_ENTITY = "param:movieEntity"
    }

    override fun createObservable(data: Map<String, Any>?): Observable<Boolean> {
        return data?.get(PARAM_MOVIE_ENTITY)
                ?.let {
                   Observable.fromCallable {
                       val entity = it as MovieEntity
                       moviesCache.save(entity)
                       return@fromCallable true
                   }
                }
                ?: Observable.error { IllegalArgumentException("MovieEntity must be provided.") }
    }

    fun save(movieEntity: MovieEntity): Observable<Boolean> {
        return observable(mapOf(PARAM_MOVIE_ENTITY to movieEntity))
    }

}