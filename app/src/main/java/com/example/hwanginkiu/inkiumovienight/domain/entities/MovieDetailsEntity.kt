package com.example.hwanginkiu.inkiumovienight.domain.entities

/**
 * Created by Yossi Segev on 07/01/2018.
 */
data class MovieDetailsEntity(
        val belongsToCollection: Any = Any(),
        val budget: Int = -1,
        val homepage: String = "",
        val imdbId: String = "",
        val overview: String = "",
        val revenue: Int = -1,
        val runtime: Int = -1,
        val status: String = "",
        val tagline: String = "",
        val reviews: List<ReviewEntity> = emptyList(),
        val videos: List<VideoEntity> = emptyList(),
        val genres: List<GenreEntity> = emptyList()
)