package com.example.chatclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.PrintWriter
import java.net.Socket
import java.util.*


const val logs = "MyLogs"

class MainActivity : AppCompatActivity() {

    private var userName = ""
    var messages = mutableListOf<String>()

    private lateinit var socket: Socket
    private lateinit var ins: Scanner
    private lateinit var outs: PrintWriter
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Dark & Light mode selection
        val mode = intent.getStringExtra(EXTRA_MESSAGE)

        when(mode) {
            "dark" -> theme.applyStyle(R.style.DarkTheme, true) // Dark theme
            "light" -> theme.applyStyle(R.style.LightTheme, true) // Light theme
        }

        setContentView(R.layout.activity_main)

        // Sets adapter and layout manager for the recycler view
        initRecycler()

        // Thread for accessing input and output streams
        Thread {
            this.socket = Socket("10.0.2.2",50000)
            this.ins = Scanner(socket.getInputStream())
            this.outs = PrintWriter(socket.getOutputStream(), true)
            Thread(InputListener(recyclerViewAdapter, this, ins)).start()
        }.start()

        sendText.visibility = View.INVISIBLE
        sendButton.visibility = View.INVISIBLE
        recyclerView.visibility = View.INVISIBLE

        // Hides the keyboard
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        // User name inputting
        userNamePopUp()

        sendButton.setOnClickListener {
            addMessage()
        }

        sendText.setOnKeyListener { _, keyCode, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                addMessage()
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
    }

    private fun initRecycler() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerViewAdapter = RecyclerViewAdapter(this@MainActivity, messages)
            adapter = recyclerViewAdapter
        }
    }

    private fun userNamePopUp() {
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Enter a username")

        val input = EditText(this)
        alert.setView(input)

        // Alert buttons
        alert.setPositiveButton("Enter") { _, _ ->
            userName = input.text.toString()
            Log.d(logs, "username is $userName")
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
                recyclerView.visibility = View.VISIBLE
                Toast.makeText(applicationContext, "Welcome $userName", Toast.LENGTH_SHORT).show()
            }
        }
        alert.setCancelable(false)
        alert.show()
    }

    private fun addMessage() {
        val userText = sendText.text.toString()
        val chatMessage = ChatMessage(userName, userText)
        Thread(ThreadMessage(chatMessage,outs)).start()
        sendText.text.clear()
    }

    // Logging
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
