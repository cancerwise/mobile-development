package com.example.cancerwise.model

data class QuizAnswer(
    val quizId: Int,
    val isMale: Boolean,
    val age: Int,
    val answers: List<QuestionAnswers>
)