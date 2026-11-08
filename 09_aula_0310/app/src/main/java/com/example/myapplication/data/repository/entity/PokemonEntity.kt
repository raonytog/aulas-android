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

// --- LIST RESPONSE ---
data class PokemonListResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("results") val results: List<PokemonResultEntity>
)

data class PokemonResultEntity(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
) {
    val id: Int
        get() {
            val parts = url.trimEnd('/').split('/')
            return parts.lastOrNull()?.toIntOrNull() ?: 1
        }

    val spriteUrl: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
}

// --- SPRITES ---
data class SpriteEntity(
    @SerializedName("other")
    val other: OtherSpritesEntity
)

data class OtherSpritesEntity(
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtworkEntity
)

data class OfficialArtworkEntity(
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
