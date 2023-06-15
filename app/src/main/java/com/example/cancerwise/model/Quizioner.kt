package com.example.cancerwise.model

data class Quizioner(
    val id: Int,
    val name: String,
    val totalCases: Int,
    val deathRate: Int,
    val star: Int,
    val time: Int,
    val desc: String,
    val img: Int,
    val questions: List<Question>
)
