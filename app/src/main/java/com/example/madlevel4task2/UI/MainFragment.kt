package com.example.madlevel4task2.UI

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.madlevel4task2.Exception.InvalidComputerMoveException
import com.example.madlevel4task2.Model.PlayerMove
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

    fun init(){
        rockImage.setOnClickListener(){
            humanPicked = PlayerMove.ROCK
            playGame(PlayerMove.ROCK)
//            playerMove.setImageResource(R.drawable.rock)
        }
        paperImage.setOnClickListener(){
            humanPicked = PlayerMove.PAPER
            playGame(PlayerMove.PAPER)
//            playerMove.setImageResource(R.drawable.paper)
        }
        scissorImage.setOnClickListener(){
            humanPicked = PlayerMove.SCISSORS
            playGame(PlayerMove.SCISSORS)
//            playerMove.setImageResource(R.drawable.scissors)
        }
    }

    override fun compareTo(){
        // TODO write compareTO for game
    }

    fun checkInput(){
        if(humanPicked.equals(PlayerMove.ROCK) && randomComputerMovePicker().equals(PlayerMove.PAPER)){

        }
        if(humanPicked.equals(PlayerMove.PAPER) && randomComputerMovePicker().equals(PlayerMove.SCISSORS)){

        }
        if(humanPicked.equals(PlayerMove.SCISSORS) && randomComputerMovePicker().equals(PlayerMove.ROCK)){
        }
    }

    fun randomComputerMovePicker(): String{
        return when ((1..3).random()) {
            1 -> PlayerMove.ROCK
            2 -> PlayerMove.PAPER
            3 -> PlayerMove.SCISSORS
            else -> throw InvalidComputerMoveException(message = "Invalid move by computer, congratulations you broke the game")
        }
    }

    fun playGame(user: String): String{
        checkInput()
//        playerMove.setImageDrawable(user)
        //TODO Set images for choice
        return " "
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