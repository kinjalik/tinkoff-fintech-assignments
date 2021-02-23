class ManualBoat (
    name: String,
    manufacturer: String,
    serialNo: Int
) : Vehicle(name, manufacturer, serialNo),
    Swimmable {
    override fun swim() {
        print("Boat $serialNumber: swimming\n")
    }
}