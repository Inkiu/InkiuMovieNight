package com.example.hwanginkiu.inkiumovienight.domain.usecases

import com.example.hwanginkiu.inkiumovienight.domain.MoviesCache
import com.example.hwanginkiu.inkiumovienight.domain.common.Transformer
import io.reactivex.Observable
import java.lang.IllegalArgumentException

class CheckFavorite(
        transformer: Transformer<Boolean>,
        private val moviesCache: MoviesCache
) : UseCase<Boolean>(transformer) {

    companion object {
        private const val PARAM_MOVIE_ENTITY = "param:movieEntity"
    }

    fun check(movieId: Int): Observable<Boolean> {
        return observable(mapOf(PARAM_MOVIE_ENTITY to movieId))
    }

    override fun createObservable(data: Map<String, Any>?): Observable<Boolean> {
        return data?.get(PARAM_MOVIE_ENTITY)
                ?.let { it -> moviesCache.get(it as Int).map { it.hasValue() } }
                ?: Observable.error { IllegalArgumentException("MovieId must be provided.") }
    }
}