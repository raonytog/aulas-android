package com.example.myapplication.data.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.data.model.PokemonModel

@Dao
interface PokemonDAO {

    @Insert
    fun insert(pokemon: PokemonModel): Long

    @Update
    fun update(pokemon: PokemonModel): Int

    @Delete
    fun delete(pokemon: PokemonModel): Int

    @Query("SELECT * FROM pokemon")
    fun getAll(): List<PokemonModel>

    @Query("SELECT * FROM pokemon WHERE name = :name")
    fun getByName(name: String): PokemonModel
}
