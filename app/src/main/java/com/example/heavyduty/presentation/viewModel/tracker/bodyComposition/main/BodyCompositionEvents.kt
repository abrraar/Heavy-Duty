package com.example.heavyduty.presentation.viewModel.tracker.bodyComposition.main

sealed interface BodyCompositionEvents {
    data class DisplayGraph(val click: String): BodyCompositionEvents
}