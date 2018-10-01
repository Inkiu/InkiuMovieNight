package com.example.hwanginkiu.inkiumovienight.presentation.entities

/**
 * Created by Yossi Segev on 09/01/2018.
 */
data class MovieDetails(
        val belongsToCollection: Any? = null,
        val budget: Int? = null,
        val homepage: String? = null,
        val imdbId: String? = null,
        val overview: String? = null,
        val revenue: Int? = null,
        val runtime: Int? = null,
        val status: String? = null,
        val tagline: String? = null,
        val videos: List<Video>? = null,
        val reviews: List<Review>? = null,
        val genres: List<String>? = null
)