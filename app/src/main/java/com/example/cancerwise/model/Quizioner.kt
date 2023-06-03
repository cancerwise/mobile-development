package com.example.cancerwise.model

data class Quizioner(
    val id: Int,
    val name: String,
    val desc: String,
    val img: Int,
    val questions: List<Question>
)
