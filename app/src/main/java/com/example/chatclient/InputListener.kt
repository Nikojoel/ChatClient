package com.example.chatclient

import android.util.Log
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
                activity.messages.add("${chatMessage.user}: ${chatMessage.message}")
                activity.runOnUiThread {
                    adapter.notifyDataSetChanged()
                }
                Log.d("MyLogs", "Incoming json: $line")

            } catch (e: Exception) {
                Log.d("MyLogs", "InputListener exception $e")
            }
        }
    }
}