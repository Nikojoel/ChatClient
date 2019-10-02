package com.example.chatclient

import kotlinx.serialization.Serializable

@Serializable
class ChatMessage(val command: String, val user: String, val message: String) {

}