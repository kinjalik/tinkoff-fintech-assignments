class StringReverseException(msg: String) : Exception(msg)

fun String.reverse(): String {
    if (this == this.reversed())
        throw StringReverseException("Unable to reverse palindrome")
    return this.reversed()
}