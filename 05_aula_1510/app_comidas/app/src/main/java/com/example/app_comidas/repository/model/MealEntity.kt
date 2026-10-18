package com.example.app_comidas.repository.model

import androidx.annotation.Nullable
import com.google.gson.annotations.SerializedName
import com.example.app_comidas.repository.model.FoodEntity

class MealEntity {
    @SerializedName("meals")
    var meals: List<FoodEntity> = listOf()
}