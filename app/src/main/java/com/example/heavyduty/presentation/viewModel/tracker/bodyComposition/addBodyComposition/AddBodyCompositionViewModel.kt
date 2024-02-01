package com.example.heavyduty.presentation.viewModel.tracker.bodyComposition.addBodyComposition

import androidx.lifecycle.ViewModel
import com.example.heavyduty.data.local.tracker.bodyComposition.main.BodyCompositionOfflineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AddBodyCompositionViewModel
    @Inject
    constructor(private val bodyCompositionRepository: BodyCompositionOfflineRepository): ViewModel()
{
    private val _state = MutableStateFlow(AddBodyCompositionUIState())
    val state = _state.asStateFlow()

    fun setPhysicalTraitToTrue(){
        _state.update {
            it.copy(
                showPhysicalTraitPrompt = true
            )
        }
    }

    fun setPhysicalTraitToFalse(){
        _state.update {
            it.copy(
                showPhysicalTraitPrompt = false
            )
        }
    }

    fun setWeightToTrue(){
        _state.update {
            it.copy(
                isWeightClicked = true
            )
        }
    }

    fun setWeightToFalse(){
        _state.update {
            it.copy(
                isWeightClicked = false
            )
        }
    }

    fun setHeightToTrue(){
        _state.update {
            it.copy(
                isHeightClicked = true
            )
        }
    }

    fun setHeightToFalse(){
        _state.update {
            it.copy(
                isHeightClicked = false
            )
        }
    }

    fun setBodyFatToTrue(){
        _state.update {
            it.copy(
                isBodyFatClicked = true
            )
        }
    }

    fun setBodyFatToFalse(){
        _state.update {
            it.copy(
                isBodyFatClicked = false
            )
        }
    }

    fun setMuscleMassToTrue(){
        _state.update {
            it.copy(
                isMuscleMassClicked = true
            )
        }
    }

    fun setMuscleMassToFalse(){
        _state.update {
            it.copy(
                isMuscleMassClicked = false
            )
        }
    }

}