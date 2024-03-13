package com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle

import com.example.heavyduty.data.local.tracker.workoutLogbook.addCycle.defaultData.listOfDefaultCycle
import com.example.heavyduty.domain.model.tracker.workoutLogbook.Cycle
import com.example.heavyduty.domain.model.tracker.workoutLogbook.ExerciseModel
import com.example.heavyduty.domain.model.tracker.workoutLogbook.WorkoutModel
import com.example.heavyduty.units.IntensityUnits

interface WorkoutLogbookEvents {
    data class CycleSelected(val cycleIndex: Int): WorkoutLogbookEvents
    data class WorkoutSelected(val workoutIndex: Int): WorkoutLogbookEvents
    data class DeleteCycleClicked(
        val status: String,
        val cycle: Cycle = Cycle(0, listOfDefaultCycle[0])
    ): WorkoutLogbookEvents
    data class AddIntensityComponent(
        val intensityUnit: IntensityUnits,
        val reps: String = "0",
        val cycleNumber: Int,
        val workoutNumber: Int,
        val exerciseModel: ExerciseModel
    ) : WorkoutLogbookEvents

    data class DeleteIntensityComponent(
        val intensityUnit: IntensityUnits,
        val reps: String = "0",
        val cycleNumber: Int,
        val workoutNumber: Int,
        val exerciseModel: ExerciseModel
    ) : WorkoutLogbookEvents

    data class DeleteExercise(
        val cycleNumber: Int,
        val workoutNumber: Int,
        val exerciseModel: ExerciseModel
    ): WorkoutLogbookEvents

    data class WorkoutDelete(
        val cycleNumber: Int,
        val workoutModel: WorkoutModel,
    ): WorkoutLogbookEvents

    data class WorkoutDeleteClicked(
        val workoutModel: WorkoutModel? = null,
        val isClicked: Boolean = false
    ): WorkoutLogbookEvents

}