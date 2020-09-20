package com.example.madlevel4task2.UI

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.madlevel4task2.Model.Game
import com.example.madlevel4task2.Model.Game.Companion.possibleMoves
import com.example.madlevel4task2.R
import com.example.madlevel4task2.Repository.GameRepository
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainFragment : Fragment() {

    private lateinit var gamesRepository: GameRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gamesRepository = GameRepository(requireContext())
        init()
    }

    /**
     * Initialises images and click listeners
     */
    private fun init() {
        scissorImage.setImageResource(R.drawable.scissors)
        rockImage.setImageResource(R.drawable.rock)
        paperImage.setImageResource(R.drawable.paper)
        rockImage.setOnClickListener { playGame(0) }
        paperImage.setOnClickListener { playGame(1) }
        scissorImage.setOnClickListener { playGame(2) }
    }

    /**
     * Checks inputs and sets text for string in main_fragment
     */
    private fun checkInput(a: Int, b: Int): Int {
        var gameResult = -1
        if (checkResult(a, b) > 0) {
            resultGame.setText(R.string.win)
            gameResult = 1
            return gameResult
        }
        if (checkResult(a, b) == 0) {
            resultGame.setText(R.string.Tie)
            gameResult = 0
            return gameResult
        }
        resultGame.setText(R.string.Lose)
        return gameResult
    }

    /**
     * Checks the input of user and computer, similair to compareTo method.
     * return int value 1 if User won, 0 if tied or -1 if user loses
     */
    private fun checkResult(userMove: Int, computerMove: Int): Int {
        if (userMove == 1 && computerMove == 0 || userMove == 2 && computerMove == 1 || userMove == 0 && computerMove == 2) { return 1 }
        if (userMove == computerMove) { return 0 }
        return -1
    }

    /**
     * Generates random number between 0 and 3
     * @param randomNumber as int
     */
    private fun randomComputerMovePicker(): Int {
        val randomNumber = (0..2).random()
        computerMove.setImageResource(possibleMoves[randomNumber])
        return randomNumber
    }


    private fun playGame(userMove: Int) {
        playerMove.setImageResource(possibleMoves[userMove])
        var computerMove = randomComputerMovePicker()
        val gameResult = checkInput(userMove, computerMove)
        addGameToDatabase(userMove, computerMove, gameResult)
    }



    private fun addGameToDatabase(userMove: Int, computerMove: Int, result: Int) {
        var win = 0
        var draw = 0
        var loss = 0
        if(result == 0){draw++}
        if(result < 0){loss++}
        if(result > 0){win++}
//        when (result) {
//            getString(R.string.win) -> win++
//            getString(R.string.Lose) -> loss++
//            getString(R.string.Tie) -> draw++
//        }

        val game = Game(userMove, computerMove, Date(), result, win, loss, draw)

        CoroutineScope(Dispatchers.IO).launch {
            gamesRepository.insertGame(game)
            CoroutineScope(Dispatchers.Main).launch {
                updateStatistics()
            }
        }
    }

    private suspend fun updateStatistics() {
        val wins = gamesRepository.getWins()
        val draws = gamesRepository.getDraws()
        val losses = gamesRepository.getLosses()
        statistics.text =
            resources.getString(R.string.statistics, wins, draws, losses)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_history -> {
                findNavController().navigate(R.id.HistoryFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}