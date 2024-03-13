package com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout.exercise.component


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import com.example.heavyduty.presentation.view.theme.BrightGreen
import com.example.heavyduty.presentation.view.theme.Green
import com.example.heavyduty.units.IntensityUnits

data class ExerciseComponentUIState(

    val listOfIntensityComponentName: MutableList<IntensityUnits> = mutableListOf(),

    val deleteExercisePrompt: Boolean = false,
    val validInput: Boolean = true,

    val positiveRepColor: Color = BrightGreen,
    val positiveText: String = "Added",
    val positiveClicked: Boolean = false,
    var positiveNum: MutableState<String> = mutableStateOf(""),

    val staticHoldColor: Color = Green,
    val staticHoldText: String = "Add",
    val staticHoldClickable: Boolean = true,
    val staticClicked: Boolean = false,
    var staticNum: MutableState<String> = mutableStateOf(""),

    val negativeColor: Color = Green,
    val negativeText: String = "Add",
    val negativeClicked: Boolean = false,
    var negativeNum: MutableState<String> = mutableStateOf(""),

    val forcedColor: Color = Green,
    val forcedText: String = "Add",
    val forcedClickable: Boolean = true,
    val forcedClicked: Boolean = false,
    var forceNum: MutableState<String> = mutableStateOf(""),

    val preExhaustColor: Color = Green,
    val preExhaustText: String = "Add",
    val preExhaustClickable: Boolean = true,
    val preExhaustClicked: Boolean = false,
)