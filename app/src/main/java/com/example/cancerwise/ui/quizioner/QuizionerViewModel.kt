package com.example.cancerwise.ui.quizioner

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.cancerwise.data.AuthRepository
import com.example.cancerwise.data.QuizRepository
import com.example.cancerwise.model.QuizAnswer
import com.example.cancerwise.model.SimpleAnswers

class QuizionerViewModel(application: Application, private val repository: QuizRepository): AndroidViewModel(application) {

    val answer = repository.answers
    val result = repository.result
    val quizioner = repository.quizioner

    fun getQuizData(id: Int) = repository.getQuizioner(id)

//    fun uploadData(item: QuizAnswer) = repository.uploadAnswers(item)

    fun predictUpload(quizId: Int, answers: SimpleAnswers) {
        repository.predictUpload(quizId, answers)
    }
}