package com.example.madlevel4task2.UI

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel4task2.Model.Game
import com.example.madlevel4task2.R
import com.example.madlevel4task2.Repository.GameRepository
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryFragment : Fragment() {

    private val gameHistory = arrayListOf<Game>()
    private val historyAdapter = HistoryAdapter(gameHistory)
    private lateinit var gameRepository: GameRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gameRepository = GameRepository(requireContext())
        initViews()
    }

    private fun initViews() {
        listHistory.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        listHistory.adapter = historyAdapter
        listHistory.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        getGameHistoryFromDatabase()
    }

    /**
     * Retrieves data from database
     */
    private fun getGameHistoryFromDatabase() {
        CoroutineScope(Dispatchers.Main).launch {
            val gameHistory = withContext(Dispatchers.IO) {
                gameRepository.getAllGames()
            }
            this@HistoryFragment.gameHistory.clear()
            this@HistoryFragment.gameHistory.addAll(gameHistory)
            this@HistoryFragment.historyAdapter.notifyDataSetChanged()
        }
    }

    /**
     * Method removes all history games from the database
     */
    private fun clearHistory() {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                gameRepository.deleteAllGames()
            }
            getGameHistoryFromDatabase()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_history, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_delete -> {
                clearHistory()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}