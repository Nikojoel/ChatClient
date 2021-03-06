package com.example.chatclient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_start.*

const val EXTRA_MESSAGE = "com.example.chatclient"

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        // Buttons for selecting the theme
        lightButton.setOnClickListener {
            startIntent("light")
        }

        darkButton.setOnClickListener {
            startIntent("dark")
        }
    }

    // Intent to start the MainActivity
    private fun startIntent(mode: String) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, mode)
        }
        startActivity(intent)
    }

    // Actionbar menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.action_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // Menu items
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.aboutItem -> infoPopUp(R.id.aboutItem)
            R.id.gitHubItem -> infoPopUp(R.id.gitHubItem)
        }
        return super.onOptionsItemSelected(item)
    }

    // Alert dialog for the menu items
    private fun infoPopUp(id: Int) {
        val alert = AlertDialog.Builder(this)

        when (id) {
            R.id.aboutItem -> alert.setMessage("Created by Niko Holopainen")
            R.id.gitHubItem -> alert.setMessage("github.com/Nikojoel/ChatClient")
        }
        alert.setPositiveButton("Close") {_,_ ->

        }
        alert.show()
    }
}
