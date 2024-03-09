package com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.cycle

import com.example.heavyduty.domain.model.tracker.workoutLogbook.Cycle

data class CycleUIState(
    val deleteState: Boolean = false,
    val listOfCycle: List<Cycle> = listOf()
)