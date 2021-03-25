class ArithmeticAndLogicUnit(
    val left: Int?,
    val right: Int?
) {
    fun readyToCalculate(operation: String): Boolean {
        if (left == null || right == null)
            return false
        if (operation == "divide" && right == 0)
            return false;
        return true
    }

    fun calculate(operation: String): Int {
        if (operation == "divide" && right == 0) {
            throw UnableToCalculateException("Zero division")
        }
        return when (operation) {
            "add" -> (left!! + right!!)
            "subtract" -> (left!! - right!!)
            "multiply" -> (left!! * right!!)
            "divide" -> (left!! / right!!)
            "and" -> (left!!.and(right!!))
            "or" -> (left!!.or(right!!))
            else -> throw UnableToCalculateException("Invalid name of operation")
        }
    }

    class UnableToCalculateException(msg: String) : Exception(msg)
}

