package com.example.hwanginkiu.inkiumovienight.domain.entities

/**
 * Created by Yossi Segev on 11/01/2018.
 */
data class ReviewEntity (
        var id: String,
        var author: String,
        var content: String? = null
)