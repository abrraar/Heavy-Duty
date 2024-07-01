package com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.cycle

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.heavyduty.domain.model.tracker.workoutLogbook.Cycle

data class CycleScreenUIState(
    val deleteState: Boolean = false,
    val orderBy: String = "Descending",
    val reverseLayout: Boolean = false,
    val listOfCycle: SnapshotStateList<Cycle> = mutableStateListOf()
)