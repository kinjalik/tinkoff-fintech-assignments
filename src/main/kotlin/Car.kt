class Car (
    _name: String,
    _manufacturer: String,
    _serialNo: Int,
    _plates: String
) : Vehicle(_name, _manufacturer, _serialNo), Engined, Rideable {
    val plates = _plates
    var power = 0

    override fun print() {
        super.print()
        print("Governmental plates: $plates\n")
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

    override fun ride() {
        if (power == 0)
            throw Exception("Can not ride with stopped engine")
        print("$plates: riding\n")
    }

    override fun signal() {
        print("BEEP-BEEP\n")
    }
}