package com.example.hwanginkiu.inkiumovienight.data.mappers

import com.example.hwanginkiu.inkiumovienight.data.entities.DetailsData
import com.example.hwanginkiu.inkiumovienight.domain.common.Mapper
import com.example.hwanginkiu.inkiumovienight.domain.entities.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Yossi Segev on 07/01/2018.
 */
@Singleton
class DetailsDataMovieEntityMapper @Inject constructor() : Mapper<DetailsData, MovieEntity>() {

    override fun mapFrom(from: DetailsData): MovieEntity {
        return MovieEntity(
                id = from.id,
                voteCount = from.voteCount,
                video = from.video,
                voteAverage = from.voteAverage,
                popularity = from.popularity,
                adult = from.adult,
                details = buildDetailEntity(from),
                title = from.title,
                posterPath = from.posterPath,
                originalLanguage = from.originalLanguage,
                originalTitle = from.originalTitle,
                backdropPath = from.backdropPath,
                releaseDate = from.releaseDate,
                overview = from.overview
        )
    }

    private fun buildDetailEntity(source: DetailsData): MovieDetailsEntity {
        return MovieDetailsEntity(
                belongsToCollection = Any(),
                budget = source.budget,
                homepage = source.homepage,
                imdbId = source.imdbId,
                overview = source.overview,
                revenue = source.revenue,
                runtime = source.runtime,
                status = "",
                tagline = source.tagline,
                reviews = buildReviewEntities(source),
                videos = buildVideoEntities(source),
                genres = buildGenreEntities(source)
        )
    }

    private fun buildVideoEntities(source: DetailsData): List<VideoEntity> {
        return source.videos.results
                .filter { it.site == VideoEntity.SOURCE_YOUTUBE && it.type == VideoEntity.TYPE_TRAILER }
                .map { VideoEntity(it.id, it.name, it.key) }
    }

    private fun buildReviewEntities(source: DetailsData): List<ReviewEntity> {
        return source.reviews.results
                .map { ReviewEntity(it.id, it.author, it.content) }
    }

    private fun buildGenreEntities(source: DetailsData): List<GenreEntity> {
        return source.genres
                .map { GenreEntity(it.id, it.name) }
    }

}