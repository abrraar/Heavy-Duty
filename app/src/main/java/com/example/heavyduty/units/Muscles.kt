package com.example.heavyduty.units

enum class Muscles {
    CHEST,
    BACK,
    SHOULDER,
    BICEPS,
    TRICEPS,
    ABS,
    QUADS,
    HAMSTRING,
    CALVES
}

fun getAllMuscles(): ArrayList<Muscles>{
    return arrayListOf(
        Muscles.CHEST,
        Muscles.BACK,
        Muscles.BICEPS,
        Muscles.TRICEPS,
        Muscles.SHOULDER,
        Muscles.QUADS,
        Muscles.HAMSTRING,
        Muscles.ABS,
        Muscles.CALVES
    )
}


fun getUpperBodyMuscles(): ArrayList<Muscles>{
    return arrayListOf(
        Muscles.CHEST,
        Muscles.BACK,
        Muscles.BICEPS,
        Muscles.TRICEPS,
        Muscles.SHOULDER
    )
}

fun getLowerBodyMuscles(): ArrayList<Muscles>{
    return arrayListOf(
        Muscles.QUADS,
        Muscles.HAMSTRING,
        Muscles.ABS,
        Muscles.CALVES
    )
}