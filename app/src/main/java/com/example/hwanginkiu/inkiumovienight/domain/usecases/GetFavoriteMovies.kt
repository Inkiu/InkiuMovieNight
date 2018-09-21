package com.example.hwanginkiu.inkiumovienight.domain.usecases

import com.example.hwanginkiu.inkiumovienight.domain.MoviesCache
import com.example.hwanginkiu.inkiumovienight.domain.common.Transformer
import com.example.hwanginkiu.inkiumovienight.domain.entities.MovieEntity
import io.reactivex.Observable

class GetFavoriteMovies(
        transformer: Transformer<List<MovieEntity>>,
        private val moviesCache: MoviesCache
) : UseCase<List<MovieEntity>>(transformer) {

    override fun createObservable(data: Map<String, Any>?): Observable<List<MovieEntity>> {
        return moviesCache.getAll()
    }
}