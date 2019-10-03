package com.example.chatclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.io.PrintWriter
import java.net.Socket
import java.util.*

const val logs = "MyLogs"

class MainActivity : AppCompatActivity() {

    var userName = ""
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

        // Prevents the soft keyboard from activating automatically
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        // User name inputting
        userNamePopUp()

        // Listener for the send button
        sendButton.setOnClickListener {
            addMessage()
        }

        // Enter key listener
        sendText.setOnKeyListener { _, keyCode, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                addMessage()
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
    } // onCreate

    // Actionbar menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.action_command_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // Menu items
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var command = ""

        when (item.itemId) {
            R.id.topItem -> {
                command = "top"
            }
            R.id.userItem -> {
                command = "users"
            }
            R.id.historyItem -> {
                command = "history"
            }
        }
        val commandMessage = ChatMessage(command,"","")
        Thread(ThreadMessage(commandMessage,outs)).start()
        return super.onOptionsItemSelected(item)
    }

    private fun initRecycler() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerViewAdapter = RecyclerViewAdapter(this@MainActivity, messages)
            adapter = recyclerViewAdapter
        }
    }

    fun alert (message: ChatMessage) {
        val alert = AlertDialog.Builder(this)
        alert.setPositiveButton("Ok") { _, _ ->

        }
        alert.setMessage(message.message)
        alert.show()
    }

    fun userNamePopUp() {
        sendText.visibility = View.INVISIBLE
        sendButton.visibility = View.INVISIBLE
        recyclerView.visibility = View.INVISIBLE
        title = ""

        val alert = AlertDialog.Builder(this)
        alert.setTitle("Enter a username")

        val input = EditText(this)
        input.setSingleLine()
        alert.setView(input)

        input.setOnKeyListener { _, keyCode, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }


        alert.setPositiveButton("Enter") { _, _ ->
            userName = input.text.toString()
            val userQuery = ChatMessage("check",userName, "check")
            Thread(ThreadMessage(userQuery,outs)).start()
        }
        alert.setCancelable(false)
        alert.show()
    }

    private fun addMessage() {
        val userText = sendText.text.toString()
        val chatMessage = ChatMessage("say", userName, userText)
        Thread(ThreadMessage(chatMessage, outs)).start()
        sendText.text.clear()
    }

    // Logging methods
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

    // Sends a disconnect message to the server when the application is destroyed
    override fun onDestroy() {
        val dcMessage = ChatMessage("disconnect", userName, "disconnect")
        Thread(ThreadMessage(dcMessage, outs)).start()
        super.onDestroy()
        Log.d(logs,"Application destroyed")
    }

}
