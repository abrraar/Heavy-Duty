package com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout

import com.example.heavyduty.domain.model.tracker.workoutLogbook.Cycle
import com.example.heavyduty.domain.model.tracker.workoutLogbook.WorkoutModel

data class WorkoutUIState(
    val deleteClicked: Boolean = false,
    val listOfCycle: List<Cycle> = listOf(),
    var cycleIndex: Int = 0,
    val workoutModel: WorkoutModel? = null,
)