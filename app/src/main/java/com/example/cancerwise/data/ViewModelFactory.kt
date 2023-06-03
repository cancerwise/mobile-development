package com.example.cancerwise.data

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cancerwise.ui.auth.AuthViewModel
import com.example.cancerwise.ui.quizioner.QuizionerViewModel

class ViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> {
                AuthViewModel(application, AuthRepository(application)) as T
            }
            modelClass.isAssignableFrom(QuizionerViewModel::class.java) -> {
                QuizionerViewModel(application, QuizRepository(application)) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}