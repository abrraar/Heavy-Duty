package com.example.heavyduty.presentation.viewModel.tracker.bodyComposition.addBodyComposition


interface AddBodyCompositionEvents {
    data class EnterPhysicalTraitClicked(val pagerState: Int): AddBodyCompositionEvents
}