package com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout

import android.util.Log
import com.example.heavyduty.domain.model.tracker.workoutLogbook.ExerciseModel
import com.example.heavyduty.domain.model.tracker.workoutLogbook.WorkoutModel
import com.example.heavyduty.units.Muscles
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

data class WorkoutComponentUIState (
    val workoutName: String = "",
    val workoutDate: String = "",
    val overallProgress: String = "",
    val muscle: ArrayList<Muscles> = arrayListOf(),
)

fun WorkoutModel.toWorkoutComponentUIState(): WorkoutComponentUIState{
    val workoutComponentUIState = MutableStateFlow(WorkoutComponentUIState())
    // Update workout Name
    workoutComponentUIState.update {
        it.copy(
            workoutName = workoutName,
            muscle = muscle,
            workoutDate = datePerformed,
            overallProgress = "${overallProgress}%"
        )
    }

    return workoutComponentUIState.value
}