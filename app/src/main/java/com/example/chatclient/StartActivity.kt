package com.example.chatclient


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_start.*

const val log = "MyLogs"
const val EXTRA_MESSAGE = "com.example.chatclient.MESSAGE"

class StartActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        lightButton.setOnClickListener {
            startIntent("light")
        }

        darkButton.setOnClickListener {
            startIntent("dark")
        }
    }

    private fun startIntent(mode: String) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, mode)
        }
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.action_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.aboutItem -> infoPopUp(R.id.aboutItem)
            R.id.gitHubItem -> infoPopUp(R.id.gitHubItem)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun infoPopUp(id: Int) {
        val alert = AlertDialog.Builder(this)

        when (id) {
            R.id.aboutItem -> alert.setMessage("About")
            R.id.gitHubItem -> alert.setMessage("GitHub")
        }
        alert.setPositiveButton("Close") {_,_ ->

        }
        alert.show()
    }
}
