package com.example.ex1.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ex1.data.model.AnimalModel

@Dao
interface AnimalDAO {
    @Insert
    fun insert(animal: AnimalModel): Long

    @Update
    fun update(p: AnimalModel): Int

    @Delete
    fun delete(p: AnimalModel)

    @Query("SELECT * FROM Animal WHERE name = :name")
    fun getByName(name: String): AnimalModel

    @Query("SELECT * FROM Animal")
    fun getAll(): List<AnimalModel>
}