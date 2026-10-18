package com.example.app_comidas.repository.model

import com.google.gson.annotations.SerializedName

data class FoodEntity(
    @SerializedName("idMeal")
    val id: String = "",

    @SerializedName("strMeal")
    val name: String = "",

    @SerializedName("strInstructions")
    val instructions: String = "",

    @SerializedName("strMealThumb")
    val imageUrl: String = ""
)