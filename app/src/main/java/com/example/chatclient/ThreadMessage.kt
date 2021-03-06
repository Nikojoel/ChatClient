package com.example.chatclient

import android.util.Log
import kotlinx.serialization.json.Json
import java.io.PrintWriter
import java.lang.Exception

class ThreadMessage(private val message: ChatMessage, private val outPut: PrintWriter) : Runnable {

    override fun run() {
        try {
            // Outputs the Json string to the server
            val chatMessageToJson = Json.stringify(ChatMessage.serializer(), message)
            outPut.println(chatMessageToJson)
            outPut.flush()
        } catch (e: Exception) {
            Log.d("MyLogs","ThreadMessage exception $e")
        }
    }
}