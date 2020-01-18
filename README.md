# Android chat client

### Description
The intent of this project is to create a chat server running from the IDE according to the requirements of a client using MVC design pattern.

### Feature specification

1. Server startup: open a socket and wait for clients to connect.

2. After the client has been connected the server asks user to input a unique username. If username is not unique server will request the user to input another username.
* After receiving a valid user name server will create a user object.
* The user object will also keep track of how many messages the user have sent.
* The new user joining will be broadcasted to all users.

3. User can start chatting and messages will be broadcasted to everyone.

4. Server will keep a list of top-5 most active chatters currently online and is able to show it to a user upon request.

### Deployment

1. Clone both the client and the server or download the zips.
* `git clone https://github.com/Nikojoel/ChatServer.git`
* `git clone https://github.com/Nikojoel/ChatClient.git`
2. Open the server with with a desired IDE and the client with Android Studio. 
3. If the `Json.parse(ChatMessage.serializer(), incomingMessage)` at `ChatConnector.kt` wont compile, rebuild the project.
4. Run the server `Main.kt` file and keep it running.
``` kotlin
fun main() {
    ChatServer().serve()
}
```
5. Open Android Studio, run a emulator and the app will open.

<p float="left">
        <img src="https://user-images.githubusercontent.com/45162563/72555571-7f04c600-38a5-11ea-87e1-37dcc72c1724.png" width="250"                 height="400">
        <img src="https://user-images.githubusercontent.com/45162563/72555574-7f9d5c80-38a5-11ea-9afc-6b8d1e7af7e0.png" width="250"                 height="400">
        <img src="https://user-images.githubusercontent.com/45162563/72555577-81ffb680-38a5-11ea-9188-5634c3ec2186.png" width="250"                 height="400">
</p>

6. Menu at the top has 3 options.
* `Users` - Displays current users in the server
* `History` - Displays history of all messages
* `Top` - Displays top 5 most active chatters

