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

<p float="left">
        <img src="https://user-images.githubusercontent.com/45162563/72555571-7f04c600-38a5-11ea-87e1-37dcc72c1724.png" width="250"                 height="400">
        <img src="https://user-images.githubusercontent.com/45162563/72555574-7f9d5c80-38a5-11ea-9afc-6b8d1e7af7e0.png" width="250"                 height="400">
        <img src="https://user-images.githubusercontent.com/45162563/72555577-81ffb680-38a5-11ea-9188-5634c3ec2186.png" width="250"                 height="400">
</p>

6. Menu at the top has 3 options
* `Users` - Displays current users in the server
* `History` - Displays history of all messages
* `Top` - Displays top 5 most active chatters
