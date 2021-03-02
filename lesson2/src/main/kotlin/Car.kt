class Car (
    name: String,
    manufacturer: String,
    serialNo: Int,
    val plates: String
) : Vehicle(name, manufacturer, serialNo),
    Engined,
    Rideable {
    private val defaultRideDistance = 50

    var power = 0
    private var mileage = 0 // Internal counter

    override fun printInformation() {
        super.printInformation()
        print("Governmental plates: $plates, mileage: $mileage\n")
    }

    override fun startEngine() {
        if (power != 0)
            throw Exception("Engine is already started")
        print("$plates: Engine started\n")
        power = 1
    }

    override fun setEnginePower(power: Int) {
        if (power < 0)
            throw Exception("Power can not be negative")
        print("$plates: Changing engine power from ${this.power} to $power\n")
        this.power = power
    }

    override fun stopEngine() {
        if (power == 0)
            throw Exception("Engine is already stopped")
        print("$plates: Engine stopped\n")
        power = 0
    }

    override fun ride(distance: Int) {
        if (power == 0)
            throw Exception("Can not ride with stopped engine")
        mileage += distance
        print("$plates: riding $distance meters\n")
    }

    override fun ride() {
        ride(defaultRideDistance)
    }

    override fun signal() {
        print("BEEP-BEEP\n")
    }

}