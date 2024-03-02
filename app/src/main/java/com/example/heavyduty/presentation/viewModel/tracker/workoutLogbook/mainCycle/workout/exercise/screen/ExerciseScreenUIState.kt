package com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout.exercise.screen

import com.example.heavyduty.domain.model.tracker.workoutLogbook.Cycle
import com.example.heavyduty.domain.model.tracker.workoutLogbook.ExerciseModel


data class ExerciseScreenUIState (
    val listOfCycle: List<Cycle> = listOf(),
    var cycleIndex: Int = 0,
    var workoutIndex: Int = 0
)