package com.example.ex1.data.room

import com.example.ex1.data.dao.AnimalDAO
import com.example.ex1.data.model.AnimalModel
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AnimalModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun AnimalDAO(): AnimalDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "mydatabase.db"
                )
                    .allowMainThreadQueries()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}