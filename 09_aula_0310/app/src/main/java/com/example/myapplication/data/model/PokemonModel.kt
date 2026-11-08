package com.example.myapplication.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.data.repository.entity.PokemonEntity

@Entity(tableName = "pokemon")
data class PokemonModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "sprite")
    val sprite: String,

    @ColumnInfo(name = "type")
    val type: String,

    @ColumnInfo(name = "hp")
    val hp: Int,

    @ColumnInfo(name = "attack")
    val attack: Int,

    @ColumnInfo(name = "defense")
    val defense: Int,

    @ColumnInfo(name = "sp_atk")
    val spAtk: Int,

    @ColumnInfo(name = "sp_def")
    val spDef: Int,

    @ColumnInfo(name = "speed")
    val speed: Int,
)

fun entityToModel(pkm: PokemonEntity) : PokemonModel {
    pkm.let { entity ->
        val pkmModel = PokemonModel(
            name = entity.name,
            sprite = entity.sprites.other.officialArtwork.frontDefault,
            type = entity.types.firstOrNull()?.type?.name ?: "",
            hp = entity.stats.getOrNull(0)?.baseStat ?: 0,
            attack = entity.stats.getOrNull(1)?.baseStat ?: 0,
            defense = entity.stats.getOrNull(2)?.baseStat ?: 0,
            spAtk = entity.stats.getOrNull(3)?.baseStat ?: 0,
            spDef = entity.stats.getOrNull(4)?.baseStat ?: 0,
            speed = entity.stats.getOrNull(5)?.baseStat ?: 0
        )
        return pkmModel
    }
}
