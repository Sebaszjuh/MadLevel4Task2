package com.example.madlevel4task2.UI

import android.R.id.message
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.madlevel4task2.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(findNavController(R.id.nav_host_fragment))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.MainFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    //TODO Database opzetten voor spelresultaten - DONE
    // Model maken voor gegevens ( resultaten, chosen action ) - IN PROGRESS
    // Adapter voor databind model
    // DAO voor database - DONE
    // REPO voor database - DONE
    // Click listeners maken op plaatjes
    // Chosen images linken aan click listeners
    // Resultaten scherm
    // Delete knop in resultaten scherm
    // Knop in main naar resultaten scherm
    // Rename fragments correctly

}