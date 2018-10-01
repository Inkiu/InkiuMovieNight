package com.example.hwanginkiu.inkiumovienight.presentation.entities

/**
 * Created by Yossi Segev on 11/01/2018.
 */
data class Review(
        val id: String,
        val author: String,
        val content: String = ""
)