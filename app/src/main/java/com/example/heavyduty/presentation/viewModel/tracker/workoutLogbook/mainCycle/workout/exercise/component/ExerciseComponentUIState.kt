package com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout.exercise.component


import androidx.compose.ui.graphics.Color
import com.example.heavyduty.presentation.view.theme.BrightGreen
import com.example.heavyduty.presentation.view.theme.Green
import com.example.heavyduty.units.IntensityUnits

data class ExerciseComponentUIState(

    val listOfIntensityComponentName: MutableList<IntensityUnits> = mutableListOf(),

    val positiveRepColor: Color = BrightGreen,
    val positiveText: String = "Added",
    val positiveClicked: Boolean = false,

    val staticHoldColor: Color = Green,
    val staticHoldText: String = "Add",
    val staticHoldClickable: Boolean = true,
    val staticClicked: Boolean = false,

    val negativeColor: Color = Green,
    val negativeText: String = "Add",
    val negativeClicked: Boolean = false,

    val forcedColor: Color = Green,
    val forcedText: String = "Add",
    val forcedClickable: Boolean = true,
    val forcedClicked: Boolean = false,

    val preExhaustColor: Color = Green,
    val preExhaustText: String = "Add",
    val preExhaustClickable: Boolean = true,
    val preExhaustClicked: Boolean = false,
)