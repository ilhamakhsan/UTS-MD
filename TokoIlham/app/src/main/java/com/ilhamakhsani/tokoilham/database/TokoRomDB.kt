package com.ilhamakhsani.Tokoilham.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ilhamakhsani.Tokoilham.model.Toko

@Database(entities = [Toko::class], version = 1, exportSchema = false)
abstract class TokoRomDB : RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE: TokoRomDB? = null

        fun getDatabase(context: Context): TokoRomDB {
            return INSTANCE ?: synchronized(this) {
                // Create database here
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TokoRomDB::class.java,
                    "Toko_db"
                )
                    .allowMainThreadQueries() //allows Room to executing task in main thread
                    .fallbackToDestructiveMigration() //allows Room to recreate database if no migrations found
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }

    abstract fun getTokoDao(): TokoDao
}