package com.example.hwanginkiu.inkiumovienight.data.api

import com.example.hwanginkiu.inkiumovienight.data.entities.ReviewData

data class ReviewResult(
        val results: List<ReviewData> = emptyList()
)
