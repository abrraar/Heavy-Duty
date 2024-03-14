package com.example.heavyduty.units

class BodyCompositionMeasurementUnits
{
    enum class MassMeasurements(val string: String){
        Kilograms("kilogram"),
        Pound("pound"),
        Percentage("%")
    }

    enum class HeightMeasurements(val string: String){
        Centimeter("cm"),
        Meter("m")
    }

}