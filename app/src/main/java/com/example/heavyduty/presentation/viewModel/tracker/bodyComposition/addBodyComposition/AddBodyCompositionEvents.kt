package com.example.heavyduty.presentation.viewModel.tracker.bodyComposition.addBodyComposition


interface AddBodyCompositionEvents {
    data class EnterPhysicalTraitClicked(val clicked: Boolean): AddBodyCompositionEvents
    data class PhysicalTraitClicked(val physicalTrait: String): AddBodyCompositionEvents
    data class SaveButtonClicked(
        val weight: String,
        val height: String,
        val bodyfat: String,
        val musclemass: String
    ): AddBodyCompositionEvents
}