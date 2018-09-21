package com.example.hwanginkiu.inkiumovienight.data.api

import com.example.hwanginkiu.inkiumovienight.data.entities.MovieData
import com.google.gson.annotations.SerializedName

data class MovieListResult(
        val page: Int = 0,
        @SerializedName("result") val movies: List<MovieData>
)