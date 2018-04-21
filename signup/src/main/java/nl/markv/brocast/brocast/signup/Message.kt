package nl.markv.brocast.brocast.signup

import java.util.*

data class Message(val user: User, val text: String, val tsSent: Date) {
}

data class Conversation(val group: Group, val messages: List<Message>) {
}
