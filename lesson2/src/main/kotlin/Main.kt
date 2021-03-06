fun main() {
    val plane = Plane("737-800", "Boeing", 2424, "DS-324").apply {
        printInformation()
        startEngine()
        takeOff()
        printInformation()
        land()
        stopEngine()
    }

    val car = Car("Model X", "Tesla", 38539, "A666MP16").apply {
        startEngine()
        ride()
        ride(100)
        stopEngine()
    }

    val boat = ManualBoat("Lodka", "Lodoproizvodstvenniy Zavod", 1337).apply {
        swim(150)
    }

    println("\nExample of polymorphic calls")
    // Example of polymorphism
    val vehicles: List<Vehicle> = mutableListOf(car, plane, boat)
    vehicles.forEach {e -> e.printInformation()}
}