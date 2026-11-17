package com.example.myapplication.data.room

import com.example.myapplication.data.DAO.PokemonDAO
import com.example.myapplication.data.model.PokemonModel
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PokemonModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun AnimalDAO(): PokemonDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "pokemon_database.db"
                )
                    .allowMainThreadQueries()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}