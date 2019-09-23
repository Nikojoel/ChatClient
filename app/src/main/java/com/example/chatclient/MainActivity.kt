package com.example.chatclient

import android.Manifest
import android.app.Application
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate


const val logs = "MyLogs"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val mode = intent.getStringExtra(EXTRA_MESSAGE)
        if (mode == "dark") {
            theme.applyStyle(R.style.DarkTheme, true)
            setContentView(R.layout.activity_main)
        } else if (mode == "light") {
            theme.applyStyle(R.style.LightTheme, true)
            setContentView(R.layout.activity_main)
        }
        super.onCreate(savedInstanceState)
        // Hides the keyboard at the start of the app
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        val userName = findViewById<EditText>(R.id.editText)



    }
}
