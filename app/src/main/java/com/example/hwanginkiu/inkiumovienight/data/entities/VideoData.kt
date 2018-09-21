package com.example.hwanginkiu.inkiumovienight.data.entities

/**
 * Created by Yossi Segev on 11/01/2018.
 */
data class VideoData(
        var id: String,
        var name: String,
        var key: String = "",
        var site: String = "",
        var type: String = ""
)