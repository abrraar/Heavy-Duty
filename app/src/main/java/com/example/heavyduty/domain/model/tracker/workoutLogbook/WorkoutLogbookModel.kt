package com.example.heavyduty.domain.model.tracker.workoutLogbook

import com.example.heavyduty.units.IntensityUnits
import com.example.heavyduty.units.Muscles

data class CycleModel(
    val cycleName: String,
    val listOfWorkout: ArrayList<WorkoutModel>,
    val startDate: String,
    val endDate: String,
    var overallProgress: Int,
)

data class WorkoutModel(
    val workoutNumber: Int,
    val workoutName: String,
    val muscle: ArrayList<Muscles>,
    val listOfExercise: ArrayList<ExerciseModel>,
    val datePerformed: String,
    var overallProgress: Int = 0,
)

data class ExerciseModel(
    val exerciseNumber: Int,
    val exerciseName: String,
    val exerciseType: String,

    var weight: Double,
    val intensitySelected: ArrayList<IntensityUnits>,
    val value: HashMap<IntensityUnits, Int>,

    var previousReps: HashMap<IntensityUnits, Int>,
    var previousWeight: Double,

    var weightDifference: Int = 0,
    var intensityDifference: HashMap<IntensityUnits, Int> = hashMapOf(),
    var totalDifference: Int = 0
)






















