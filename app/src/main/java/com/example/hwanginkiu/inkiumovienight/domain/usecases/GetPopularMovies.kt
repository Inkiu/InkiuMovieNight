package com.example.hwanginkiu.inkiumovienight.domain.usecases

import com.example.hwanginkiu.inkiumovienight.domain.MoviesRepository
import com.example.hwanginkiu.inkiumovienight.domain.common.Transformer
import com.example.hwanginkiu.inkiumovienight.domain.entities.MovieEntity
import io.reactivex.Observable

open class GetPopularMovies(transformer: Transformer<List<MovieEntity>>,
                            private val moviesRepository: MoviesRepository) : UseCase<List<MovieEntity>>(transformer) {

    override fun createObservable(data: Map<String, Any>?): Observable<List<MovieEntity>> {
        return moviesRepository.getMovies()
    }

}