# Android chat client

1. Clone both the client and the server or download the zips 
* `git clone https://github.com/Nikojoel/ChatServer.git`
* `git clone https://github.com/Nikojoel/ChatClient.git`
2. Open the server with with a desired IDE and the client with Android Studio 
3. If the `Json.parse(ChatMessage.serializer(), incomingMessage)` at `ChatConnector.kt` wont compile, rebuild the project
4. Run the server `Main.kt` file and keep it running
``` kotlin
fun main() {
    ChatServer().serve()
}
```
5. Open Android Studio, run a emulator and the app will open
