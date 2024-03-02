package com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout.exercise.component

import com.example.heavyduty.units.IntensityUnits

sealed interface ExerciseComponentEvents {
    data class IntensityComponentClicked(val component: IntensityUnits): ExerciseComponentEvents
}