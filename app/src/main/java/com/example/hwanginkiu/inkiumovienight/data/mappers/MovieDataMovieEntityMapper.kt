package com.example.hwanginkiu.inkiumovienight.data.mappers

import com.example.hwanginkiu.inkiumovienight.data.entities.MovieData
import com.example.hwanginkiu.inkiumovienight.domain.common.Mapper
import com.example.hwanginkiu.inkiumovienight.domain.entities.MovieEntity
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by Yossi Segev on 11/11/2017.
 */
@Singleton
class MovieDataMovieEntityMapper @Inject constructor() : Mapper<MovieData, MovieEntity>() {

    override fun mapFrom(from: MovieData): MovieEntity {
        return MovieEntity(
                id = from.id,
                voteCount = from.voteCount,
                voteAverage = from.voteAverage,
                popularity = from.popularity,
                adult = from.adult,
                title = from.title,
                posterPath = from.posterPath,
                originalLanguage = from.originalLanguage,
                backdropPath = from?.backdropPath ?: "",
                originalTitle = from.originalTitle,
                releaseDate = from.releaseDate,
                overview = from.overview
        )
    }
}
