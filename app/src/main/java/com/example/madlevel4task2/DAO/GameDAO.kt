package com.example.madlevel4task2.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.madlevel4task2.Model.Game

@Dao
interface GameDAO {

    @Query("SELECT * FROM gameStatistic")
    suspend fun getAllGames(): List<Game>

    @Insert
    suspend fun insertGame(game: Game)

    @Delete
    suspend fun deleteGame(game: Game)

    @Query("DELETE FROM gameStatistic")
    suspend fun deleteAllGames()

    @Query("SELECT COUNT(win) FROM gameStatistic WHERE win == 1")
    suspend fun getWins(): Int

    @Query("SELECT COUNT(tie) FROM gameStatistic WHERE tie == 1")
    suspend fun getTies(): Int

    @Query("SELECT COUNT(loss) FROM gameStatistic WHERE loss == 1")
    suspend fun getLosses(): Int

}