package com.example.cancerwise.data

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.cancerwise.api.ApiConfig
import com.example.cancerwise.model.FakeRewardDataSource
import com.example.cancerwise.model.Question
import com.example.cancerwise.model.QuizAnswer
import com.example.cancerwise.model.Quizioner
import com.example.cancerwise.model.Result
import com.example.cancerwise.model.SimpleAnswers
import com.example.cancerwise.model.SimpleResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class QuizRepository(application: Application) {

//    private var _result = MutableLiveData<Resource<Result>>()
//    val result = _result

    private var _result = MutableLiveData<Resource<SimpleResponse>>()
    val result = _result

    private var _answers = MutableLiveData<Resource<QuizAnswer>>()
    val answers = _answers

    private val _quizioner = MutableLiveData<Resource<List<Quizioner>>>()
    val quizioner = _quizioner

    fun predictUpload(quizId: Int, answer: SimpleAnswers) {
        _result.postValue(Resource.Loading())

        when (quizId) {
            0 -> {
                ApiConfig.getApiService()
                    .predictLung(answer)
                    .enqueue(object : Callback<SimpleResponse> {

                        override fun onResponse(
                            call: Call<SimpleResponse>,
                            response: Response<SimpleResponse>,
                        ) {
                            if (response.code() == 200) {
                                _result.postValue(Resource.Success(response.body()))
                            } else {
                                _result.postValue(Resource.Error("Something Went Wrong!"))
                            }
                        }

                        override fun onFailure(call: Call<SimpleResponse>, t: Throwable) {
                            _result.postValue(Resource.Error(t.message))
                        }

                    })
            }
            1 -> {
                ApiConfig.getApiService()
                    .predictBrain(answer)
                    .enqueue(object : Callback<SimpleResponse> {

                        override fun onResponse(
                            call: Call<SimpleResponse>,
                            response: Response<SimpleResponse>,
                        ) {
                            if (response.code() == 200) {
                                _result.postValue(Resource.Success(response.body()))
                            } else {
                                _result.postValue(Resource.Error("Something Went Wrong!"))
                            }
                        }

                        override fun onFailure(call: Call<SimpleResponse>, t: Throwable) {
                            _result.postValue(Resource.Error(t.message))
                        }

                    })
            }
            2 -> {
                ApiConfig.getApiService()
                    .predictCervical(answer)
                    .enqueue(object : Callback<SimpleResponse> {

                        override fun onResponse(
                            call: Call<SimpleResponse>,
                            response: Response<SimpleResponse>,
                        ) {
                            if (response.code() == 200) {
                                _result.postValue(Resource.Success(response.body()))
                            } else {
                                _result.postValue(Resource.Error("Something Went Wrong!"))
                            }
                        }

                        override fun onFailure(call: Call<SimpleResponse>, t: Throwable) {
                            _result.postValue(Resource.Error(t.message))
                        }

                    })
            }
        }
    }

    fun getQuizioner(id: Int): Quizioner = FakeRewardDataSource.dummyProduct[id]

}