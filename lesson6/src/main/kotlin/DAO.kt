interface DAO {
    fun getAllEntities(): List<Entity>
    fun getEntityById(id: Int): Entity?
}

class Entity(
    val id: Int,
    val name: String,
    val size: Int
)