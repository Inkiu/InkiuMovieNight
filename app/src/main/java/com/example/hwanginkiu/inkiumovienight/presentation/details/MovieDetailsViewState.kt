package com.example.hwanginkiu.inkiumovienight.presentation.details

import com.example.hwanginkiu.inkiumovienight.presentation.entities.Video


/**
 * Created by Yossi Segev on 10/01/2018.
 */
data class MovieDetailsViewState(
        val isLoading: Boolean = true,
        val title: String = "",
        val overview: String = "",
        val videos: List<Video> = emptyList(),
        val homepage: String = "",
        val releaseDate: String = "",
        val votesAverage: Double = 0.0,
        val backdropUrl: String = "",
        val genres: List<String> = emptyList()
)