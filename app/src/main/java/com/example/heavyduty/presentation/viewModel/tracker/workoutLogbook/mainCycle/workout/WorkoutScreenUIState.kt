package com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.heavyduty.domain.model.tracker.workoutLogbook.Cycle
import com.example.heavyduty.domain.model.tracker.workoutLogbook.WorkoutModel
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.cycle.CycleComponentUIState

data class WorkoutScreenUIState(
    val baseCycle: Boolean = false,
    val deleteClicked: Boolean = false,
    val listOfCycle: SnapshotStateList<Cycle> = mutableStateListOf(),
    var cycleIndex: Int = 0,
    val workoutModel: WorkoutModel? = null,
)