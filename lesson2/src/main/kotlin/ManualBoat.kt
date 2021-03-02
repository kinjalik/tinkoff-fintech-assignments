class ManualBoat (
    name: String,
    manufacturer: String,
    serialNo: Int
) : Vehicle(name, manufacturer, serialNo),
    Swimmable {
    private var swimDistance = 0
    override fun swim(distance: Int) {
        print("Boat $serialNumber: swimming $distance meters\n")
        swimDistance += distance
    }

    override fun printInformation() {
        super.printInformation()
        print("Overall swam distance: $swimDistance meters")
    }
}