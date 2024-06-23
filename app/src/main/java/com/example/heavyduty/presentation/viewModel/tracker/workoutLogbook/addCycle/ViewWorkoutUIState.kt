package com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.addCycle


import com.example.heavyduty.domain.model.tracker.workoutLogbook.WorkoutModel

data class ViewWorkoutUIState(
    val useCycle: Boolean = false,
    val cycleName: String = ""
)