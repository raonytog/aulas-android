package com.example.myapplication.data.repository.entity
import com.google.gson.annotations.SerializedName

data class PokemonDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("stats")
    val stats: List<BaseStats>,

    @SerializedName("types")
    val types: List<String>
)

data class BaseStats(
    @SerializedName("base_stat")
    val baseStat: Int,

    @SerializedName("stat")
    val stat_name: String
)