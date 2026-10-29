package com.example.myapplication.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.model.ProductModel

@Dao
interface ProductDAO {

    @Insert
    fun insert(p: ProductModel): Long

    @Update
    fun update(p: ProductModel): Int

    @Delete
    fun delete(p: ProductModel)

    @Query("SELECT * FROM Product WHERE id = :id")
    fun getById(id: Int): ProductModel

    @Query("SELECT * FROM Product WHERE is_available = :av")
    fun getAvailables(av: Int): List<ProductModel>
}
