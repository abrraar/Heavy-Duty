package com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle

import com.example.heavyduty.domain.model.tracker.workoutLogbook.Cycle

data class WorkoutLogbookUIState(
    val listOfCycle: List<Cycle> = listOf()
)