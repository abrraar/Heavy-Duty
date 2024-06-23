package com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle

import com.example.heavyduty.domain.model.tracker.workoutLogbook.Cycle
import com.example.heavyduty.domain.model.tracker.workoutLogbook.ExerciseModel
import com.example.heavyduty.domain.model.tracker.workoutLogbook.WorkoutModel
import com.example.heavyduty.units.IntensityUnits

interface CycleEvents {
    data class CycleSelected(val cycleIndex: Int): CycleEvents
    data class DeleteCycleClicked(
        val cycle: Cycle
    ): CycleEvents

    data class OrderByCycle(
        val order: String
    ): CycleEvents

}

interface WorkoutEvents{
    data class WorkoutSelected(val workoutIndex: Int): WorkoutEvents
    data class WorkoutDeleteClicked(
        val workoutModel: WorkoutModel? = null,
        val isClicked: Boolean = false
    ): WorkoutEvents
    data class WorkoutDelete(
        val cycleNumber: Int,
        val workoutModel: WorkoutModel,
    ): WorkoutEvents
}

interface ExerciseEvents{
    data class AddReps(
        val intensityUnit: IntensityUnits,
        val reps: String = "0",
        val cycleNumber: Int,
        val workoutNumber: Int,
        val exerciseModel: ExerciseModel
    ): ExerciseEvents
    data class AddIntensityComponent(
        val intensityUnit: IntensityUnits,
        val cycleNumber: Int,
        val workoutNumber: Int,
        val exerciseModel: ExerciseModel
    ) : ExerciseEvents

    data class DeleteIntensityComponent(
        val intensityUnit: IntensityUnits,
        val cycleNumber: Int,
        val workoutNumber: Int,
        val exerciseModel: ExerciseModel
    ) : ExerciseEvents

    data class AddWeight(
        val weight: String,
        val exerciseModel: ExerciseModel,
        val cycleIndex: Int,
        val workoutIndex: Int,
        val index: Int
    ): ExerciseEvents

    data class DeleteExercise(
        val cycleNumber: Int,
        val workoutNumber: Int,
        val exerciseModel: ExerciseModel
    ): ExerciseEvents
}