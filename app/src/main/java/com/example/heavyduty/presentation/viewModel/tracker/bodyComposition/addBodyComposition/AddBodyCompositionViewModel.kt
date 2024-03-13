package com.example.heavyduty.presentation.viewModel.tracker.bodyComposition.addBodyComposition

import androidx.lifecycle.ViewModel
import com.example.heavyduty.data.local.Constants
import com.example.heavyduty.data.local.tracker.bodyComposition.main.BodyCompositionOfflineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AddBodyCompositionViewModel
    @Inject
    constructor(
        private val bodyCompositionRepository: BodyCompositionOfflineRepository): ViewModel()
{
    private val _state = MutableStateFlow(AddBodyCompositionUIState())
    val state = _state.asStateFlow()
    private var bodyCompositionList: ArrayList<String> = arrayListOf()

    fun onAddBodyCompositionEvents(events: AddBodyCompositionEvents){
        when(events){
           is AddBodyCompositionEvents.EnterPhysicalTraitClicked -> {
               when (events.clicked)
               {
                   true -> {
                       _state.update {
                           it.copy(
                               showPhysicalTraitPrompt = true
                           )
                       }
                   }
                   false -> {
                       _state.update {
                           it.copy(
                               showPhysicalTraitPrompt = false
                           )
                       }
                   }
               }
           }
           is AddBodyCompositionEvents.PhysicalTraitClicked -> {
               when(events.physicalTrait){
                   Constants.WEIGHT -> {
                       if(!_state.value.isWeightClicked){
                           bodyCompositionList.add(Constants.WEIGHT)

                           _state.update {
                               it.copy(
                                   isWeightClicked = true,
                                   bodyCompositionList = bodyCompositionList
                               )
                           }

                       }
                       else{
                           bodyCompositionList.remove(Constants.WEIGHT)
                           _state.update {
                               it.copy(
                                   isWeightClicked = false,
                                   bodyCompositionList = bodyCompositionList
                               )
                           }

                       }

                   }
                   Constants.HEIGHT -> {
                       if(!_state.value.isHeightClicked){
                           bodyCompositionList.add(Constants.HEIGHT)
                           _state.update {
                               it.copy(
                                   isHeightClicked = true,
                                   bodyCompositionList = bodyCompositionList
                               )
                           }

                       }
                       else{
                           bodyCompositionList.remove(Constants.HEIGHT)
                           _state.update {
                               it.copy(
                                   isHeightClicked = false,
                                   bodyCompositionList = bodyCompositionList
                               )
                           }

                       }
                   }
                   Constants.BODYFAT -> {
                       if(!_state.value.isBodyFatClicked){
                           bodyCompositionList.add(Constants.BODYFAT)
                           _state.update {
                               it.copy(
                                   isBodyFatClicked = true,
                                   bodyCompositionList = bodyCompositionList
                               )
                           }

                       }
                       else{
                           bodyCompositionList.remove(Constants.BODYFAT)
                           _state.update {
                               it.copy(
                                   isBodyFatClicked = false,
                                   bodyCompositionList = bodyCompositionList
                               )
                           }

                       }
                   }
                   Constants.MUSCLEMASS -> {
                       if(!_state.value.isMuscleMassClicked){
                           bodyCompositionList.add(Constants.MUSCLEMASS)
                           _state.update {
                               it.copy(
                                   isMuscleMassClicked = true,
                                   bodyCompositionList = bodyCompositionList
                               )
                           }

                       }
                       else{
                           bodyCompositionList.remove(Constants.MUSCLEMASS)
                           _state.update {
                               it.copy(
                                   isMuscleMassClicked = false,
                                   bodyCompositionList = bodyCompositionList
                               )
                           }

                       }
                   }
               }
           }
        }
    }
}