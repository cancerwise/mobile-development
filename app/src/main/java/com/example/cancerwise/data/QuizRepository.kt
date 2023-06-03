package com.example.cancerwise.data

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.cancerwise.model.FakeRewardDataSource
import com.example.cancerwise.model.QuestionAnswers
import com.example.cancerwise.model.QuizAnswer
import com.example.cancerwise.model.Quizioner
import com.example.cancerwise.model.Result
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class QuizRepository(application: Application) {

    private var _result = MutableLiveData<Resource<Result>>()
    val result = _result

    private var _answers = MutableLiveData<Resource<QuizAnswer>>()
    val answers = _answers

    private val _quizioner = MutableLiveData<Resource<List<Quizioner>>>()
    val quizioner = _quizioner

    fun uploadAnswers(answer: QuizAnswer) {
        _result.postValue(Resource.Loading())

        try {
            _answers.postValue(Resource.Success(QuizAnswer(answer.quizId, answer.isMale, answer.age, answer.answers)))

            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            val current = LocalDateTime.now().format(formatter)

            result.postValue(Resource.Success(Result(1, 1,1,true,12, 100, "Healthy", current, "good life")))

        } catch (e: Exception) {
            _result.postValue(Resource.Error(e.toString()))
        }

    }

    fun getQuizioner() {

        _quizioner.postValue(Resource.Loading())

        try {
            _quizioner.postValue(Resource.Success(FakeRewardDataSource.dummyProduct))

        } catch (e: Exception) {
            _quizioner.postValue(Resource.Error(e.toString()))

        }
    }

}