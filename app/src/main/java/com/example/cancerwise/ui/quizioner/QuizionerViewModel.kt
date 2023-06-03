package com.example.cancerwise.ui.quizioner

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.cancerwise.data.AuthRepository
import com.example.cancerwise.data.QuizRepository
import com.example.cancerwise.model.QuizAnswer

class QuizionerViewModel(application: Application, private val repository: QuizRepository): AndroidViewModel(application) {

    val answer = repository.answers
    val result = repository.result
    val quizioner = repository.quizioner

    fun getQuizData() = repository.getQuizioner()

    fun uploadData(item: QuizAnswer) = repository.uploadAnswers(item)
}