package com.example.hwanginkiu.inkiumovienight.presentation

import com.example.hwanginkiu.inkiumovienight.domain.common.Mapper
import com.example.hwanginkiu.inkiumovienight.domain.entities.MovieEntity
import com.example.hwanginkiu.inkiumovienight.presentation.entities.Movie
import com.example.hwanginkiu.inkiumovienight.presentation.entities.MovieDetails
import com.example.hwanginkiu.inkiumovienight.presentation.entities.Review
import com.example.hwanginkiu.inkiumovienight.presentation.entities.Video

/**
 * Created by Yossi Segev on 11/11/2017.
 */

class MovieEntityMovieMapper : Mapper<MovieEntity, Movie>() {

    companion object {
        const val posterBaseUrl = "https://image.tmdb.org/t/p/w342"
        const val backdropBaseUrl = "https://image.tmdb.org/t/p/w780"
        const val youTubeBaseUrl = "https://www.youtube.com/watch?v="
    }


    override fun mapFrom(from: MovieEntity): Movie {

        val details = MovieDetails(
                belongsToCollection = from.details.belongsToCollection,
                budget = from.details.budget,
                homepage = from.details.homepage,
                imdbId = from.details.imdbId,
                overview = from.details.overview,
                revenue = from.details.revenue,
                runtime = from.details.runtime,
                status = from.details.status,
                tagline = from.details.tagline,
                genres = from.details.genres.map { it.name },
                videos = from.details.videos.map { Video(id = it.id, name = it.name, url = youTubeBaseUrl + it.youtubeKey) },
                reviews = from.details.reviews.map { Review(id = it.id, author = it.author, content = it.content ?: "") }
        )

        return Movie(
                id = from.id,
                voteCount = from.voteCount,
                video = from.video,
                voteAverage = from.voteAverage,
                title = from.title,
                popularity = from.popularity,
                originalLanguage = from.originalLanguage,
                posterPath = from.posterPath.let { posterBaseUrl + from.posterPath },
                backdropPath = from.backdropPath.let { backdropBaseUrl + from.backdropPath },
                originalTitle = from.originalTitle,
                adult = from.adult,
                releaseDate = from.releaseDate,
                overview = from.overview,
                details = details
        )
    }
}