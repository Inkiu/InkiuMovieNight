package com.example.hwanginkiu.inkiumovienight.domain.usecases

import com.example.hwanginkiu.inkiumovienight.domain.MoviesRepository
import com.example.hwanginkiu.inkiumovienight.domain.common.Transformer
import com.example.hwanginkiu.inkiumovienight.domain.entities.MovieEntity
import io.reactivex.Observable

class GetSearchMovies(
        transformer: Transformer<List<MovieEntity>>,
        private val moviesRepository: MoviesRepository
) : UseCase<List<MovieEntity>>(transformer) {

    companion object {
        private const val PARAM_SERACH_QUERY = "param:search_query"
    }

    fun search(query: String): Observable<List<MovieEntity>> {
        val data = mapOf(PARAM_SERACH_QUERY to query)
        return observable(data)
    }

    override fun createObservable(data: Map<String, Any>?): Observable<List<MovieEntity>> {
        return data?.get(PARAM_SERACH_QUERY)
                ?.let {
                    moviesRepository.search(it as String)
                } ?: Observable.just(emptyList())
    }

}