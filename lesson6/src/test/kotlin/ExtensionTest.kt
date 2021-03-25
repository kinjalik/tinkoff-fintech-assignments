import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.math.exp

class ExtensionTest {
    @Test
    fun `Reverse non-palindrome string`() {
        val test = "TestString100PercentNotPalindrome"
        val expected = "emordnilaPtoNtnecreP001gnirtStseT"

        assertEquals(test.reverse(), expected)
    }

    @Test
    fun `Exception thrown if called on palindrome`() {
        val test = "ABBAABBA"
        var thrown = false

        try {
            test.reverse()
        } catch (e: StringReverseException) {
            thrown = true
        }

        assertEquals(thrown, true)
    }

    @Test
    fun `Text of exception on palindrome`() {
        val testInput = "ABBAABBA"
        val expectedMessage = "Unable to reverse palindrome"

        try {
            testInput.reverse()
            assert(false)
        } catch (e: StringReverseException) {
            assertEquals(e.message, expectedMessage)
        }
    }
}