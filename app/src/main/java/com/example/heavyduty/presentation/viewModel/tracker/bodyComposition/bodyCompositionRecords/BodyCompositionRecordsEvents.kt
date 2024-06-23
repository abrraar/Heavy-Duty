package com.example.heavyduty.presentation.viewModel.tracker.bodyComposition.bodyCompositionRecords

interface BodyCompositionRecordsEvents {
    data class RecordDeleteClicked(
        val status: String,
        val item: Any? = null,
        ): BodyCompositionRecordsEvents
}