class Plane(
    name: String,
    manufacturer: String,
    serialNumber: Int,
    val label: String
) : Vehicle(name, manufacturer, serialNumber),
    Flyable,
    Engined{

    var power = 0
    private var onFly = false

    override fun startEngine() {
        if (power != 0)
            throw Exception("Engine already started")
        print("$label: Engine started\n")
        power = 1
    }

    override fun setEnginePower(power: Int) {
        if (power < 0)
            throw Exception("Power can not be negative")
        print("$label: Engine power on $power\n")
        this.power = power
    }

    override fun stopEngine() {
        if (power == 0)
            throw Exception("Engine is already stopped")
        print("$label: Engine stopped\n")
    }

    override fun takeOff() {
        if (onFly)
            throw Exception("Can not take off: already on fly\n")
        print("$label to dispatch: Taking off\n")
        onFly = true
    }

    override fun land() {
        if (!onFly)
            throw Exception("Can not land: already on the ground\n")
        print("$label to dispatch: Landing\n")
        onFly = false
    }

    override fun printInformation() {
        super.printInformation()
        print("Designation $label, now ${if (onFly) "in air" else "on the ground"}\n")
    }
}