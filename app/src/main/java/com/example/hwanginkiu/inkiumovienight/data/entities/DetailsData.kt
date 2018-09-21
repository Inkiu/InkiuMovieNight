package com.example.hwanginkiu.inkiumovienight.data.entities

import com.example.hwanginkiu.inkiumovienight.data.api.ReviewResult
import com.example.hwanginkiu.inkiumovienight.data.api.VideoResult
import com.google.gson.annotations.SerializedName

/**
 * Created by Yossi Segev on 07/01/2018.
 */
data class DetailsData(

        @SerializedName("adult")
        val adult: Boolean = false,

        @SerializedName("budget")
        val budget: Int = -1,

        @SerializedName("genres")
        val genres: List<GenreData> = emptyList(),

        @SerializedName("videos")
        val videos: VideoResult = VideoResult(),

        @SerializedName("reviews")
        val reviews: ReviewResult = ReviewResult(),

        @SerializedName("homepage")
        val homepage: String = "",

        @SerializedName("id")
        val id: Int = -1,

        @SerializedName("imdb_id")
        val imdbId: String = "",

        @SerializedName("popularity")
        val popularity: Double = 0.0,

        @SerializedName("revenue")
        val revenue: Int = -1,

        @SerializedName("runtime")
        val runtime: Int = -1,

        @SerializedName("tagline")
        val tagline: String = "",

        @SerializedName("video")
        val video: Boolean = false,

        @SerializedName("vote_average")
        val voteAverage: Double = 0.0,

        @SerializedName("vote_count")
        val voteCount: Int = 0,

        @SerializedName("title")
        val title: String,

        @SerializedName("poster_path")
        val posterPath: String,

        @SerializedName("original_language")
        val originalLanguage: String,

        @SerializedName("original_title")
        val originalTitle: String,

        @SerializedName("backdrop_path")
        val backdropPath: String,

        @SerializedName("overview")
        val overview: String,

        @SerializedName("release_date")
        val releaseDate: String
)