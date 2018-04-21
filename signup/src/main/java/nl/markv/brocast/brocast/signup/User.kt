package nl.markv.brocast.brocast.signup

data class User(val id: String, val name: String) {
}

class UserList() {
    /// Get all users
    fun all(): List<User> {
        return listOf(
                User("1", "Mark"),
                User("2", "Yoeri"),
                User("3", "Jasper"),
                User("4", "Sander")
        )
    }
}