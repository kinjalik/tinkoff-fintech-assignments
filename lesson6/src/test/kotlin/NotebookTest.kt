import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class NotebookTest {

    @Test
    fun `mock of dsl`() {
        val slot = slot<String>()

        val notebook = mockk<Notebook> {
            every { title } returns "Test notebook"
            every { description } returns "This is test notebook for test of something"
            every { capacity } returns 3
            every { notes } returns mutableListOf(
                "Some strange note 1",
                "Some strange note 2",
                "Some strange note 3",
            )
            every { add(capture(slot)) } returns false
        }

        assertAll("Notebook should contains only 3 notes",
            { assertEquals(notebook.title, "Test notebook") },
            { assertEquals(notebook.description, "This is test notebook for test of something") },
            { assertEquals(notebook.capacity, 3) },
            { assertEquals(notebook.notes.size, 3) },
            { assertEquals(notebook.notes[0], "Some strange note 1") },
            { assertEquals(notebook.notes[1], "Some strange note 2") },
            { assertEquals(notebook.notes[2], "Some strange note 3") },
            { assertEquals(notebook.add("AFJWALFKJAWLKF"), false) },
            { assertEquals(notebook.notes.size, 3) }
        )
    }
}