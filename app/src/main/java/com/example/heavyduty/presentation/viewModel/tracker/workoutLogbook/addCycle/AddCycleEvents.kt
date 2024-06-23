package com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.addCycle

interface AddCycleEvents {
    data class CycleSelected(val cycleIndex: Int): AddCycleEvents
    data class UseCycleClicked(
        val screen: String,
        val clicked: Boolean,
        val cycleName: String = "",
        ): AddCycleEvents
    data class ConfirmClicked(
        val cycleName: String
    ):AddCycleEvents
}