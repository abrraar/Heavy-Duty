package com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle

interface WorkoutLogbookEvents {
    data class CycleSelected(val cycleIndex: Int): WorkoutLogbookEvents
    data class WorkoutSelected(val workoutIndex: Int): WorkoutLogbookEvents
}