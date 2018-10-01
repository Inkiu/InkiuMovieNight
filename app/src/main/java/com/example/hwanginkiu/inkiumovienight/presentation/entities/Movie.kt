package com.example.hwanginkiu.inkiumovienight.presentation.entities

data class Movie(
        val id: Int = 0,
        val voteCount: Int = 0,
        val video: Boolean = false,
        val voteAverage: Double = 0.0,
        val title: String,
        val popularity: Double = 0.0,
        val posterPath: String? = null,
        val originalLanguage: String,
        val originalTitle: String,
        val backdropPath: String? = null,
        val adult: Boolean = false,
        val releaseDate: String,
        val details: MovieDetails? = null,
        val isFavorite: Boolean = false,
        val overview: String? = null
) {
    fun containsVideos(): Boolean {
        return details?.videos != null && details.videos.isNotEmpty()
    }

    fun containsReviews(): Boolean {
        return details?.reviews != null && details.reviews.isNotEmpty()
    }

    fun containsGenres(): Boolean {
        return details?.genres != null && details.genres.isNotEmpty()
    }
}