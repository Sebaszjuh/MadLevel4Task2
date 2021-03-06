package com.example.madlevel4task2.UI

import android.graphics.Color
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
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }



    /**
     * Initialises images and click listeners
     */
    private fun init() {
        CoroutineScope(Dispatchers.Main).launch {
            updateStatistics()
        }
        scissorImage.setImageResource(R.drawable.scissors)
        scissorImage.setBackgroundColor(Color.rgb(98,0,238))
        rockImage.setImageResource(R.drawable.rock)
        rockImage.setBackgroundColor(Color.rgb(98,0,238))
        paperImage.setImageResource(R.drawable.paper)
        paperImage.setBackgroundColor(Color.rgb(98,0,238))
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

    /**
     * Method for the gamemechanic to let the computerMove pick an action.
     */
    private fun playGame(userMove: Int) {
        playerMove.setImageResource(possibleMoves[userMove])
        val computerMove = randomComputerMovePicker()
        val gameResult = checkInput(userMove, computerMove)
        addGameToDatabase(userMove, computerMove, gameResult)
    }

    /**
     * Adds result of the game to the database, calls update of statistics
     */
    private fun addGameToDatabase(userMove: Int, computerMove: Int, result: Int) {
        var win = 0
        var tie = 0
        var loss = 0
        if(result == 0){tie++}
        if(result < 0){loss++}
        if(result > 0){win++}

        val game = Game(userMove, computerMove, Date(), result, win, loss, tie)

        CoroutineScope(Dispatchers.IO).launch {
            gamesRepository.insertGame(game)
            CoroutineScope(Dispatchers.Main).launch {
                updateStatistics()
            }
        }
    }

    /**
     * Method to update the onscreen win,loss,tie
     */
    private suspend fun updateStatistics() {
        val wins = gamesRepository.getWins()
        val tie = gamesRepository.getTies()
        val losses = gamesRepository.getLosses()
        statistics.text =
            resources.getString(R.string.statistics, wins, tie, losses)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gamesRepository = GameRepository(requireContext())
        init()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    /**
     * Button in toolbar on top to switch screens.
     */
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