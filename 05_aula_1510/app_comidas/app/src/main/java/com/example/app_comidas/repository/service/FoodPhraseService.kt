package com.example.app_comidas.repository.service

import com.example.app_comidas.repository.model.FoodPhraseEntity

import retrofit2.Call
import retrofit2.http.GET

interface FoodPhraseService {
    @GET("")
    fun getPhrase(): Call<FoodPhraseEntity>
}