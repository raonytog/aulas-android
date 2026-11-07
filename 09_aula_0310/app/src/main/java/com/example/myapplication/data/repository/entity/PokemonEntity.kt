package com.example.myapplication.data.repository.entity

import com.google.gson.annotations.SerializedName

data class PokemonEntity(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("sprites")
    val sprites: SpriteEntity,

    @SerializedName("stats")
    val stats: List<BaseStats>,

    @SerializedName("types")
    val types: List<PokemonTypeResponse>
)

// --- SPRITES ---e
data class SpriteEntity(
    @SerializedName("other")
    val other: OtherSpritesEntity
)

data class OtherSpritesEntity(
    @SerializedName("home")
    val home: HomeSpriteEntity
)

data class HomeSpriteEntity(
    @SerializedName("front_default")
    val frontDefault: String
)

// --- STATS ---
data class BaseStats(
    @SerializedName("base_stat")
    val baseStat: Int,

    @SerializedName("stat")
    val stat: StatDetail
)

data class StatDetail(
    @SerializedName("name")
    val name: String
)

// --- TYPES ---
data class PokemonTypeResponse(
    @SerializedName("type")
    val type: TypeDetail
)

data class TypeDetail(
    @SerializedName("name")
    val name: String
)
