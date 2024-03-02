package com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.addCycle

interface AddCycleEvents {
    data class CycleSelected(val cycleIndex: Int): AddCycleEvents
    data class UseCycleClicked(
        val key: String,
        val clicked: Boolean,
        val cycleName: String): AddCycleEvents
}