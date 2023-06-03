package com.example.cancerwise.model

import java.util.Date

data class Result(
    val resultId: Int,
    val quizId: Int,
    val Uid: Int,
    val isMale: Boolean,
    val age: Int,
    val score: Int,
    val status: String,
    val date: String,
    val suggestions: String
)