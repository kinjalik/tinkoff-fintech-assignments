fun main(args: Array<String>) {
    val plane = Plane("737-800", "Boeing", 2424, "DS-324")
    plane.print()
    plane.startEngine()
    plane.takeOff()
    plane.print()
    plane.land()
    plane.stopEngine()

    val car = Car("Model X", "Tesla", 38539, "A666MP16")
    car.print()
    car.startEngine()
    car.ride()
    car.stopEngine()

    val boat = ManualBoat("Lodka", "Lodoproizvodstvenniy Zavod", 1337)
    boat.print()
    boat.swim()

}