package com.example.madlevel4task2.UI

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel4task2.Model.Game
import com.example.madlevel4task2.Model.Game.Companion.possibleMoves
import com.example.madlevel4task2.R
import kotlinx.android.synthetic.main.item_history.view.*

class HistoryAdapter(private val gameHistory: List<Game>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        )
    }

    override fun getItemCount(): Int = gameHistory.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(gameHistory[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(game: Game) {
            itemView.itemResult.text = parseResult(game.gameResult)
            itemView.itemDate.text = game.playDate.toString()
            itemView.playerImage.setImageResource(possibleMoves[game.chosenAction])
            itemView.computerImage.setImageResource(possibleMoves[game.computerAction])
        }

        /**
         * App uses -1, 0 and 1 everywhere to see if player won the game. It parses the gameresult into a string
         */
        private fun parseResult(gameResult: Int): String {
            if (gameResult == 0) {
                return "Tie"
            }
            if (gameResult == 1) {
                return "You win!"
            }
            return "You Lose"
        }
    }

}