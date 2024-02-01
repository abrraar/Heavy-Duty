package com.example.heavyduty.presentation.viewModel.tracker.bodyComposition.addBodyComposition

data class AddBodyCompositionUIState (
    val numberOfComponents: Int = 0,
    val showPhysicalTraitPrompt: Boolean = false,

    val isWeightClicked: Boolean = false,
    val isHeightClicked: Boolean = false,
    val isBodyFatClicked: Boolean = false,
    val isMuscleMassClicked: Boolean = false,
)