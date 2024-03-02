package com.example.heavyduty.domain.model.tracker.workoutLogbook

import com.example.heavyduty.units.IntensityUnits
import com.example.heavyduty.units.Muscles
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.io.Serializable
import java.util.Date
import javax.inject.Singleton


data class CycleModel(
    val cycleName: String,
    val listOfWorkout: ArrayList<WorkoutModel>,
    val startDate: String,
    val endDate: String,
    val overallProgress: Double,
)

data class WorkoutModel(
    val workoutNumber: Int,
    val workoutName: String,
    val muscle: ArrayList<Muscles>,
    val listOfExercise: ArrayList<ExerciseModel>,
    val datePerformed: String,
    val overallProgress: Double
)


data class ExerciseModel(
    val exerciseNumber: Int,
    val exerciseName: String,
    val exerciseType: String,
    val value: HashMap<IntensityUnits, Int>,
    val previousReps: Int,
    val increasedRate: Double
)






















