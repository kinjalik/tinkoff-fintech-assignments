package entities

data class Community(
    private val id: Int,
    private val name: String,
    private val membersCount: Int,
    private val isClosed: Boolean,
);
