package com.example.cancerwise.api
import com.example.cancerwise.model.SimpleAnswers
import com.example.cancerwise.model.SimpleResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST("/predict/lung-cancer")
    fun predictLung(
        @Body answers: SimpleAnswers
    ):Call<SimpleResponse>

    @POST("/predict/cervical-cancer")
    fun predictCervical(
        @Body answers: SimpleAnswers
    ):Call<SimpleResponse>

    @POST("/predict/brain-tumor")
    fun predictBrain(
        @Body answers: SimpleAnswers
    ):Call<SimpleResponse>

}