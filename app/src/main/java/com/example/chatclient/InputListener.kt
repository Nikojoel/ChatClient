package com.example.chatclient

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.serialization.json.Json
import java.util.*

class InputListener(private val adapter: RecyclerViewAdapter, private val activity: MainActivity, private val inPut: Scanner) : Runnable {

    override fun run() {
        while (true) {
            try {
                val line = inPut.nextLine()
                val chatMessage = Json.parse(ChatMessage.serializer(), line)

                when {
                    chatMessage.command == "invalid" -> activity.runOnUiThread {
                        activity.userNamePopUp()
                        Toast.makeText(activity.applicationContext, "Invalid username", Toast.LENGTH_SHORT).show()
                    }
                    chatMessage.command == "valid" -> activity.runOnUiThread {
                        activity.sendText.visibility = View.VISIBLE
                        activity.sendButton.visibility = View.VISIBLE
                        activity.recyclerView.visibility = View.VISIBLE
                        activity.title = "@${activity.userName}"
                        Toast.makeText(activity.applicationContext, "Connected as ${activity.userName}", Toast.LENGTH_SHORT).show()
                    }
                    chatMessage.command == "say" -> {
                        activity.messages.add("${chatMessage.user}: ${chatMessage.message}")
                        activity.runOnUiThread {
                            adapter.notifyDataSetChanged()
                        }
                        Log.d("MyLogs", "Incoming json: $line")
                    }
                }
            } catch (e: Exception) {
                Log.d("MyLogs", "InputListener exception $e")
                break
            }
        }
    }
}