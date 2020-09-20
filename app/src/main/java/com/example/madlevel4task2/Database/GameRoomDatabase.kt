package com.example.madlevel4task2.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.madlevel4task2.DAO.GameDAO
import com.example.madlevel4task2.Model.Game


// Database van product class
@Database(entities = [Game::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class GameRoomDatabase : RoomDatabase(){

    // instantieren van DAO
    abstract fun gameDao(): GameDAO


    // Companion is een soort 'vervanger'  voor static
    companion object {
        private const val DATABASE_NAME = "SHOPPING_LIST_DATABASE"

        // Volatile makes it writeable for other instances
        @Volatile
        private var GameStatisticInstance: GameRoomDatabase? = null


        // Builder method om de database te bouwen
        fun getDatabase(context: Context): GameRoomDatabase? {
            if (GameStatisticInstance == null) {
                synchronized(GameRoomDatabase::class.java) {
                    if (GameStatisticInstance == null) {
                        GameStatisticInstance =
                            Room.databaseBuilder(context.applicationContext,GameRoomDatabase::class.java, DATABASE_NAME).build()
                    }
                }
            }
            return GameStatisticInstance
        }
    }

}
