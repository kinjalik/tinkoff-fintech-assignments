import io.mockk.coVerify
import org.junit.jupiter.api.Test
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.math.exp

class ArithmeticAndLogicUnitTest {
    @Test
    fun `Null left, non-null right operands`() {
        val calc = ArithmeticAndLogicUnit(null, 42)

        assertEquals(calc.readyToCalculate("sum"), false)
    }

    @Test
    fun `Non-null left, null right operands`() {
        val calc = ArithmeticAndLogicUnit(42, null)

        assertEquals(calc.readyToCalculate("sum"), false)
    }

    @Test
    fun `Both operands are null`() {
        val calc = ArithmeticAndLogicUnit(null, null)
        assertEquals(calc.readyToCalculate("divide"), false)
    }

    @Test
    fun `Non-null operands, not division`() {
        val calc = ArithmeticAndLogicUnit(42, 24)
        assertEquals(calc.readyToCalculate("sum"), true)
    }

    @Test
    fun `Non-null operands, right is non-zero, division`() {
        val calc = ArithmeticAndLogicUnit(42, 2)
        assertEquals(calc.readyToCalculate("divide"), true)
    }

    @Test
    fun `Non-null operands, right is zero, division`() {
        val calc = ArithmeticAndLogicUnit(42, 0)
        assertEquals(calc.readyToCalculate("divide"), false)
    }

    @Test
    fun `Attempts of illegal operation`() {
        val calc = ArithmeticAndLogicUnit(42, 2)
        var exceptionThrown = false
        try {
            calc.calculate("illegal")
        } catch (e: ArithmeticAndLogicUnit.UnableToCalculateException) {
            exceptionThrown = true
        }
        assertEquals(exceptionThrown, true)
    }

    @Test
    fun `Attempt of empty operation`() {
        val calc = ArithmeticAndLogicUnit(42, 2)
        var exceptionThrown = false
        try {
            calc.calculate("")
        } catch (e: ArithmeticAndLogicUnit.UnableToCalculateException) {
            exceptionThrown = true
        }
        assertEquals(exceptionThrown, true)
    }

    @Test
    fun `Division by zero`() {
        val calc = ArithmeticAndLogicUnit(42, 0)
        var exceptionThrown = false
        try {
            calc.calculate("divide")
        } catch (e: ArithmeticAndLogicUnit.UnableToCalculateException) {
            exceptionThrown = true
        }

        assertEquals(exceptionThrown, true)
    }

    @Test
    fun `Division by non-zero`() {
        val a = 42
        val b = 2
        val expected = 21
        val calc = ArithmeticAndLogicUnit(a, b)

        val res = calc.calculate("divide")

        assertEquals(res, expected)
    }

    @Test
    fun `Operation of add`() {
        val a = 42
        val b = 3
        val expected = 45
        val calc = ArithmeticAndLogicUnit(a, b)

        val res = calc.calculate("add")

        assertEquals(res, expected)
    }

    @Test
    fun `Operation of subtract`() {
        val a = 42
        val b = 2
        val expected = 40
        val calc = ArithmeticAndLogicUnit(a, b)

        val res = calc.calculate("subtract")
        assertEquals(res, expected)
    }

    @Test
    fun `Operation of multiplication`() {
        val a = 21
        val b = 2
        val expected = 42
        val calc = ArithmeticAndLogicUnit(a, b)

        val res = calc.calculate("multiply")
        assertEquals(res, expected)
    }

    @Test
    fun `Operation of logic and`() {
        val a = 42
        val b = 13
        val expected = 8
        val calc = ArithmeticAndLogicUnit(a, b)

        val res = calc.calculate("and")
        assertEquals(res, expected)
    }

    @Test
    fun `Operation of logic or`() {
        val a = 65
        val b = 8
        val expected = 73
        val calc = ArithmeticAndLogicUnit(a, b)

        val res = calc.calculate("or")
        assertEquals(res, expected)
    }
}