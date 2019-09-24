package com.example.chatclient

import android.Manifest
import android.app.Application
import android.content.DialogInterface
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate


const val logs = "MyLogs"

class MainActivity : AppCompatActivity() {

    private var userName = ""

    override fun onCreate(savedInstanceState: Bundle?) {

        // Dark & Light mode selection
        val mode = intent.getStringExtra(EXTRA_MESSAGE)

        if (mode == "dark") {
            theme.applyStyle(R.style.DarkTheme, true)
            setContentView(R.layout.activity_main)

        } else if (mode == "light") {
            theme.applyStyle(R.style.LightTheme, true)
            setContentView(R.layout.activity_main)
        }

        super.onCreate(savedInstanceState)

        // Hides the keyboard
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        // User name inputting
        userNamePopUp()

    }

    private fun userNamePopUp() {
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Enter a username")

        val input = EditText(this)
        alert.setView(input)

        // Alert button listeners
        alert.setPositiveButton("Enter"){_,_ ->
            userName = input.text.toString()
            Log.d(logs, userName)
        }

        alert.setNegativeButton("Cancel"){_,_ ->
            input.text.clear()

        }

        alert.setOnDismissListener {
            if (userName == "") {
                Toast.makeText(applicationContext, "Please enter a username", Toast.LENGTH_SHORT).show()
                userNamePopUp()
            } else {
                Toast.makeText(applicationContext, "Welcome $userName", Toast.LENGTH_SHORT).show()
            }
        }
        alert.show()
    }

}
