package com.example.hwanginkiu.inkiumovienight.domain.usecases

import com.example.hwanginkiu.inkiumovienight.domain.MoviesCache
import com.example.hwanginkiu.inkiumovienight.domain.common.Transformer
import com.example.hwanginkiu.inkiumovienight.domain.entities.MovieEntity
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import java.lang.IllegalArgumentException

/**
 * Created by Yossi Segev on 21/01/2018.
 */
class RemoveFavoriteMovie(transformer: Transformer<Boolean>,
                          private val moviesCache: MoviesCache): UseCase<Boolean>(transformer) {

    companion object {
        private const val PARAM_MOVIE_ENTITY = "param:movieEntity"
    }

    fun remove(movieEntity: MovieEntity): Observable<Boolean> {
        val data = HashMap<String, MovieEntity>()
        data[PARAM_MOVIE_ENTITY] = movieEntity
        return observable(data)
    }

    override fun createObservable(data: Map<String, Any>?): Observable<Boolean> {

        val movieEntity = data?.get(PARAM_MOVIE_ENTITY)

        movieEntity?.let {
            return Observable.fromCallable {
                val entity = it as MovieEntity
                moviesCache.remove(entity)
                return@fromCallable false
            }
        }?: return Observable.error { IllegalArgumentException("MovieEntity must be provided.") }
    }

}