package com.example.app_comidas.repository.service

import com.example.app_comidas.repository.model.FoodEntity
import com.example.app_comidas.repository.model.MealEntity

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodPhraseService {
    @GET("search.php")
    fun getPhrase(@Query("s") foodName: String): Call<MealEntity>
}