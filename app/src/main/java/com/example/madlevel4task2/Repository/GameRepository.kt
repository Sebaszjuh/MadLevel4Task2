package com.example.madlevel4task2.Repository


import android.content.Context
import com.example.madlevel4task2.DAO.GameDAO
import com.example.madlevel4task2.Database.GameRoomDatabase
import com.example.madlevel4task2.Model.Game

class GameRepository(context: Context) {

    private var gameDAO: GameDAO

    init {
        val gameRoomDatabase = GameRoomDatabase.getDatabase(context)
        gameDAO = gameRoomDatabase!!.gameDao()
    }

    suspend fun getAllGames(): List<Game> {
        return gameDAO.getAllGames()
    }

    suspend fun insertGame(game: Game) {
        gameDAO.insertGame(game)
    }

    suspend fun deleteGame(game: Game) {
        gameDAO.deleteGame(game)
    }

    suspend fun deleteAllGames() {
        gameDAO.deleteAllGames()
    }

    suspend fun getWins(): Int {
        return gameDAO.getWins()
    }

    suspend fun getDraws(): Int {
        return gameDAO.getDraws()
    }

    suspend fun getLosses(): Int {
        return gameDAO.getLosses()
    }

}