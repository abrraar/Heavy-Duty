package com.example.heavyduty.units

import com.example.heavyduty.R

enum class BodyCompositionComponents(val component: Int)
{
    WEIGHT(R.string.weight),
    HEIGHT(R.string.height),
    BODYFAT(R.string.body_fat),
    MUSCLEMASS(R.string.muscle_mass),
    BMI(R.string.bmi)
}

fun getAllBodyComposition(): List<BodyCompositionComponents>{
    return listOf(
        BodyCompositionComponents.WEIGHT,
        BodyCompositionComponents.HEIGHT,
        BodyCompositionComponents.BODYFAT,
        BodyCompositionComponents.MUSCLEMASS,
        BodyCompositionComponents.BMI
    )
}

fun getBodyCompositionComponent(component: Int): BodyCompositionComponents?{
    val map = BodyCompositionComponents.entries
        .associateBy(BodyCompositionComponents::component)
    return map[component]
}