package com.example.cancerwise.model

data class Question(
    val quizId: Int,
    val number: Int,
    val question: String,
    val expectedAnswer: String,
    val note: Int
)