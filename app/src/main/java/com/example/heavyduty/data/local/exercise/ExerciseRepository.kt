package com.example.heavyduty.data.local.exercise

import com.example.heavyduty.domain.model.tracker.workoutLogbook.Cycle
import kotlinx.coroutines.flow.Flow


interface ExerciseRepository {
    fun getLastExercise(): Flow<Cycle>
}