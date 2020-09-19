package com.example.madlevel4task2.UI

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.madlevel4task2.Model.Game.Companion.possibleMoves
import com.example.madlevel4task2.R
import kotlinx.android.synthetic.main.fragment_main.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainFragment : Fragment() {

    lateinit var humanPicked: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    fun init() {
        scissorImage.setImageResource(R.drawable.scissors)
        rockImage.setImageResource(R.drawable.rock)
        paperImage.setImageResource(R.drawable.paper)
        rockImage.setOnClickListener { playGame(possibleMoves[0]) }
        paperImage.setOnClickListener { playGame(possibleMoves[1]) }
        scissorImage.setOnClickListener { playGame(possibleMoves[2]) }
    }

    private fun checkInput(a: Int, b: Int) {
        if (checkResult(a, b) > 0) {
            resultGame.setText(R.string.win)
        }
        if (checkResult(a, b) == 0) {
            resultGame.setText(R.string.Tie)
        }
        resultGame.setText(R.string.Lose)
    }

    //Not working correctly
    private fun checkResult(a: Int, b: Int): Int {
        if (a.equals(1) && b.equals(0)|| a.equals(2) && b.equals(1) || a.equals(0) && b.equals(2)) {
            return 1
        }
        if (a.equals(b)) {
            return 0
        }
        return -1
    }

    private fun randomComputerMovePicker(): Int {
        val randomNumber = (0..2).random()
        computerMove.setImageResource(possibleMoves[randomNumber])
        return randomNumber
    }

    fun playGame(userMove: Int) {
        playerMove.setImageResource(userMove)
        checkInput(userMove, randomComputerMovePicker())
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