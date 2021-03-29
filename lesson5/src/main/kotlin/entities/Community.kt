package entities

data class Community(
    private val id: Int,
    val name: String,
    val membersCount: Int,
    val isClosed: Boolean,
);
