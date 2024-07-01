package com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout.exercise.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.heavyduty.domain.model.tracker.workoutLogbook.Cycle
import com.example.heavyduty.domain.model.tracker.workoutLogbook.ExerciseModel
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.cycle.CycleComponentUIState
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout.exercise.component.ExerciseComponentUIState


data class ExerciseScreenUIState (
    val baseCycle: Boolean = false,
    val listOfCycle: SnapshotStateList<Cycle> = mutableStateListOf(),
    var cycleIndex: Int = 0,
    var workoutIndex: Int = 0
)