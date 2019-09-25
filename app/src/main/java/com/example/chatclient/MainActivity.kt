package com.example.chatclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_main.*

const val logs = "MyLogs"

class MainActivity : AppCompatActivity() {

    private var userName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Dark & Light mode selection
        val mode = intent.getStringExtra(EXTRA_MESSAGE)

        if (mode == "dark") {
            theme.applyStyle(R.style.DarkTheme, true)
            setContentView(R.layout.activity_main)

        } else if (mode == "light") {
            theme.applyStyle(R.style.LightTheme, true)
            setContentView(R.layout.activity_main)
        }



        sendText.visibility = View.INVISIBLE
        sendButton.visibility = View.INVISIBLE

        // Hides the keyboard
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        // User name inputting
        userNamePopUp()

        sendButton.setOnClickListener {
            // Send

            sendText.text.clear()
        }
    }

    private fun userNamePopUp() {

        val alert = AlertDialog.Builder(this)
        alert.setTitle("Enter a username")

        val input = EditText(this)
        alert.setView(input)

        // Alert button listeners
        alert.setPositiveButton("Enter") { _, _ ->
            userName = input.text.toString()
            Log.d(logs, userName)
        }

        alert.setNegativeButton("Cancel") { _, _ ->
            input.text.clear()
        }

        alert.setOnDismissListener {
            if (userName == "") {
                Toast.makeText(applicationContext, "Please enter a username", Toast.LENGTH_SHORT).show()
                userNamePopUp()

            } else {
                sendText.visibility = View.VISIBLE
                sendButton.visibility = View.VISIBLE
                Toast.makeText(applicationContext, "Welcome $userName", Toast.LENGTH_SHORT).show()
            }
        }
        alert.show()
    }

    override fun onStart() {
        super.onStart()
        Log.d(logs,"Application started")
    }

    override fun onResume() {
        super.onResume()
        Log.d(logs,"Application resumed")
    }

    override fun onPause() {
        super.onPause()
        Log.d(logs,"Application paused")
    }

    override fun onStop() {
        super.onStop()
        Log.d(logs,"Application stopped")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(logs,"Application destroyed")
    }

}
