package com.example.myapplication.data.model

import com.example.myapplication.data.repository.entity.PokemonEntity
import com.google.firebase.firestore.DocumentId

data class PokemonModel(
    @DocumentId
    val id: String = "",
    val name: String = "",
    val sprite: String = "",
    val type: String = "",
    val hp: Int = 0,
    val attack: Int = 0,
    val defense: Int = 0,
    val spAtk: Int = 0,
    val spDef: Int = 0,
    val speed: Int = 0
)

fun entityToModel(pkm: PokemonEntity): PokemonModel {
    return PokemonModel(
        name = pkm.name,
        sprite = pkm.sprites.other.officialArtwork.frontDefault,
        type = pkm.types.firstOrNull()?.type?.name ?: "",
        hp = pkm.stats.getOrNull(0)?.baseStat ?: 0,
        attack = pkm.stats.getOrNull(1)?.baseStat ?: 0,
        defense = pkm.stats.getOrNull(2)?.baseStat ?: 0,
        spAtk = pkm.stats.getOrNull(3)?.baseStat ?: 0,
        spDef = pkm.stats.getOrNull(4)?.baseStat ?: 0,
        speed = pkm.stats.getOrNull(5)?.baseStat ?: 0
    )
}