abstract class Vehicle(
    val name: String,
    val manufacturer: String,
    val serialNumber: Int
) {

    open fun printInformation() {
        print("$name by $manufacturer: $serialNumber\n")
    }

}