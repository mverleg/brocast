package nl.markv.brocast.brocast.signup

data class Group(val id: String, val name: String)

class GroupList() {
    /// Get all users
    fun all(): List<Group> {
        return listOf(
                Group("1", "Group1"),
                Group("2", "Group2"),
                Group("3", "Group3"),
                Group("4", "Group4")
        )
    }

    fun search(query: String): List<Group> {
        return all().filter{ query in it.name }.toList()
    }
}
