# Android chat client

1. Clone both the client and the server or download the zips 
* `git clone https://github.com/Nikojoel/ChatServer.git`
* `git clone https://github.com/Nikojoel/ChatClient.git`
2. If the `Json.parse(ChatMessage.serializer(), incomingMessage)` at `ChatConnector.kt` wont compile, rebuild the project
3. Run the server `Main.kt` file with a desired IDE and keep it running
```
fun main() {
    ChatServer().serve()
}
```
4. Open Android Studio, run a emulator and the app will open
