package com.example.chatclient

import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.serialization.json.Json
import java.util.*

class InputListener(private val adapter: RecyclerViewAdapter, private val activity: MainActivity, private val inPut: Scanner) : Runnable {

    override fun run() {
        while (true) {
            try {
                val line = inPut.nextLine()
                val chatMessage = Json.parse(ChatMessage.serializer(), line)

                // Statements for the commands
                when (chatMessage.command) {
                    // Username inputting
                    "invalid" -> activity.runOnUiThread {
                        activity.userNamePopUp()
                        Toast.makeText(activity.applicationContext, "Invalid username", Toast.LENGTH_SHORT).show()
                    }
                    // Accepts the username and lets the user to start chatting
                    "valid" -> activity.runOnUiThread {
                        activity.sendText.visibility = View.VISIBLE
                        activity.sendButton.visibility = View.VISIBLE
                        activity.recyclerView.visibility = View.VISIBLE
                        activity.title = "@${activity.userName}"
                        Toast.makeText(activity.applicationContext, "Connected as ${activity.userName}", Toast.LENGTH_SHORT).show()
                    }
                    // Actual chatting
                    "say" -> {
                        activity.messages.add("${chatMessage.user}: ${chatMessage.message}")
                        activity.runOnUiThread {
                            adapter.notifyDataSetChanged()
                            activity.recyclerView.scrollToPosition(adapter.itemCount - 1)
                        }
                    }
                    // Displays current users, chat history and the top chatters
                    "users", "history", "top" -> {
                        activity.runOnUiThread {
                            activity.alert(chatMessage)
                        }
                    }
                }
            } catch (e: Exception) {
                Log.d("MyLogs", "InputListener exception $e")
                break
            }
        }
    }
}