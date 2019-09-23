package com.example.chatclient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

const val EXTRA_MESSAGE = "com.example.chatclient.MESSAGE"

class StartActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val lightModeButton = findViewById<ImageButton>(R.id.lightButton)
        val darkModeButton = findViewById<ImageButton>(R.id.darkButton)

        lightModeButton.setOnClickListener {
            val mode = "light"
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, mode)
            }
            startActivity(intent)
        }

        darkModeButton.setOnClickListener {
            val mode = "dark"
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, mode)
            }
            startActivity(intent)
        }


    }
}
