class ManualBoat (
    _name: String,
    _manufacturer: String,
    _serialNo: Int
) : Vehicle(_name, _manufacturer, _serialNo), Swimmable {
    override fun swim() {
        print("Boat $serialNumber: swimming\n")
    }
}