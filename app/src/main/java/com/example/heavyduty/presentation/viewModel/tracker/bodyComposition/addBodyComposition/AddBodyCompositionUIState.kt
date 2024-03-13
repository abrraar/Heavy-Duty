package com.example.heavyduty.presentation.viewModel.tracker.bodyComposition.addBodyComposition

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class AddBodyCompositionUIState (
    val numberOfComponents: Int = 0,
    val showPhysicalTraitPrompt: Boolean = false,

    val bodyCompositionList: ArrayList<String> = arrayListOf(),

    val isWeightClicked: Boolean = false,
    val weightValue: MutableState<String> = mutableStateOf(""),

    val isHeightClicked: Boolean = false,
    val heightValue: MutableState<String> = mutableStateOf(""),

    val isBodyFatClicked: Boolean = false,
    val bodyFatValue: MutableState<String> = mutableStateOf(""),

    val isMuscleMassClicked: Boolean = false,
    val muscleMassValue: MutableState<String> = mutableStateOf(""),
)