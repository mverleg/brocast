package nl.markv.brocast.brocast.signup

data class User(val id: String, val name: String) {
}

class UserList(val users: List<User>) {
    /// Get all users
    fun all(): List<User> {
        return users
    }

    companion object {
        fun mockAll(): UserList {
            return UserList(listOf(
                    User("1", "Jasper")
                    , User("2", "Mark")
                    , User("3", "Sander")
                    , User("4", "Yoeri")
            ))
        }
    }

    // Get users matched by search query
    fun search(query: String): List<User> {
        return all().filter{ query in it.name }.toList()
    }
}
