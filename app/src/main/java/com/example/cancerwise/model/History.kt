package com.example.cancerwise.model

import java.util.Date
import java.util.concurrent.locks.Condition

data class History (
    val quizTitle: String,
    val score: Int,
    val status: String,
    val date: String,
    val img: Int
)