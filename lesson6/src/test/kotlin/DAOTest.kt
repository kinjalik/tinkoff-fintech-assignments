import io.mockk.MockK
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class DAOTest {
    private val listOfDemoEntities = listOf(
        Entity(0, "Entity Zero", 492),
        Entity(1, "Entity One", 249),
        Entity(2, "Entity Two", 983),
        Entity(3, "Entity Three", 439),
        Entity(4, "Entity Four", 338)
    )

    fun getMockedDAO(): DAO {
        return mockk<DAO> {
            val sl = slot<Int>()
            every { getAllEntities() } returns listOfDemoEntities
            every { getEntityById(capture(sl)) } answers {
                when (sl.captured) {
                    in 0..5 -> listOfDemoEntities[sl.captured]
                    else -> null
                }
            }
        }
    }

    @Test
    fun `Get all entities`() {
        val dao = getMockedDAO()

        assertEquals(dao.getAllEntities(), listOfDemoEntities)
    }

    @Test
    fun `Get entity with valid id`() {
        val dao = getMockedDAO()
        val id = 2

        val res = dao.getEntityById(id)

        assertEquals(res, listOfDemoEntities[id])
    }

    @Test
    fun `Get entity with invalid id`() {
        val dao = getMockedDAO()
        val id = 10

        val res = dao.getEntityById(id)

        assertNull(res)
    }
}