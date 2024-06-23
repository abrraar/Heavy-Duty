package com.example.heavyduty.units


sealed class ExerciseType(val exerciseType: String){
    data object COMPOUND: ExerciseType ("Compound Exercise")
    data object ISOLATION: ExerciseType ("Isolation Exercise")
}
