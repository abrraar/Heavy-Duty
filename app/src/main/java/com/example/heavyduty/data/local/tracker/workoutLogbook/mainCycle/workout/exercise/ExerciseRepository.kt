package com.example.heavyduty.data.local.tracker.workoutLogbook.mainCycle.workout.exercise

import com.example.heavyduty.domain.model.tracker.workoutLogbook.Cycle
import kotlinx.coroutines.flow.Flow


interface ExerciseRepository {
    fun getLastExercise(): Flow<Cycle>
}