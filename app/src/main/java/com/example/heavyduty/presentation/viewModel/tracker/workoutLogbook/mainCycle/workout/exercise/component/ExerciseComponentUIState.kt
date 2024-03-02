package com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout.exercise.component


import androidx.compose.ui.graphics.Color
import com.example.heavyduty.presentation.view.theme.BrightGreen
import com.example.heavyduty.presentation.view.theme.Green
import com.example.heavyduty.units.IntensityUnits

data class ExerciseComponentUIState(

    val listOfIntensityComponentName: MutableList<IntensityUnits> = mutableListOf(),

    val positiveRepColor: Color = BrightGreen,
    val positiveText: String = "Added",
    val positiveClickable: Boolean = true,

    val staticHoldColor: Color = Green,
    val staticHoldText: String = "Add",
    val staticHoldClickable: Boolean = true,

    val negativeColor: Color = Green,
    val negativeText: String = "Add",
    val negativeClickable: Boolean = true,

    val forcedColor: Color = Green,
    val forcedText: String = "Add",
    val forcedClickable: Boolean = true,

    val preExhaustColor: Color = Green,
    val preExhaustText: String = "Add",
    val preExhaustClickable: Boolean = true,
)