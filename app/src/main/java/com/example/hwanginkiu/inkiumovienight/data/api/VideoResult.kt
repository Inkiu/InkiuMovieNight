package com.example.hwanginkiu.inkiumovienight.data.api

import com.example.hwanginkiu.inkiumovienight.data.entities.VideoData


/**
 * Created by Yossi Segev on 11/01/2018.
 */
data class VideoResult(
        val results: List<VideoData> = emptyList()
)