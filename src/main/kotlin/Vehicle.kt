abstract class Vehicle(
    _name: String,
    _manufacturer: String,
    _serialNo: Int) {

    val name: String = _name
    val manufacturer = _manufacturer
    val serialNumber = _serialNo

    open fun print() {
        print("$name by $manufacturer: $serialNumber\n")
    }

}