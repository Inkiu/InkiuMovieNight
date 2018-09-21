package com.example.hwanginkiu.inkiumovienight.data.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName


/**
 * Created by Yossi Segev on 11/11/2017.
 */
@Entity(tableName = "movies")
data class MovieData(

        @SerializedName("id")
        @PrimaryKey
        val id: Int = -1,

        @SerializedName("vote_count")
        val voteCount: Int = 0,

        @SerializedName("vote_average")
        val voteAverage: Double = 0.0,

        @SerializedName("adult")
        val adult: Boolean = false,

        @SerializedName("popularity")
        val popularity: Double = 0.0,

        @SerializedName("title")
        val title: String,

        @SerializedName("poster_path")
        val posterPath: String = "",

        @SerializedName("original_language")
        val originalLanguage: String,

        @SerializedName("original_title")
        val originalTitle: String,

        @SerializedName("backdrop_path")
        val backdropPath: String = "",

        @SerializedName("release_date")
        val releaseDate: String,

        @SerializedName("overview")
        val overview: String = ""
)