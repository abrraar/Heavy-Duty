package com.example.heavyduty.units


enum class IntensityUnits(val component: String){
        Positive("Positive Rep"),
        Static("Static Hold"),
        Negative("Negative Rep"),
        Forced("Forced Rep"),
        PreExhaust("Pre Exhaust")
    }
    fun getAllIntensityUnits(): List<IntensityUnits>{
        return listOf(
            IntensityUnits.Positive,
            IntensityUnits.Static,
            IntensityUnits.Negative,
            IntensityUnits.Forced,
            IntensityUnits.PreExhaust
        )
    }

    fun getIntensityUnits(intensityUnit: IntensityUnits): String{
        var intensity = ""
        for(units in getAllIntensityUnits()){
           if (units == intensityUnit){
               intensity = units.component
           }
       }
        return intensity
    }
