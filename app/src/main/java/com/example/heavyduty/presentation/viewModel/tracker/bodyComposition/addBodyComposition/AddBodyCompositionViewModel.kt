package com.example.heavyduty.presentation.viewModel.tracker.bodyComposition.addBodyComposition

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.heavyduty.data.local.Constants
import com.example.heavyduty.data.local.tracker.bodyComposition.addBodyComposition.AddBodyCompositionOfflineRepository
import com.example.heavyduty.data.local.tracker.bodyComposition.addBodyComposition.AddBodyCompositionRepository
import com.example.heavyduty.data.local.tracker.bodyComposition.main.BodyCompositionOfflineRepository
import com.example.heavyduty.domain.model.tracker.bodyComposition.BodyFat
import com.example.heavyduty.domain.model.tracker.bodyComposition.Height
import com.example.heavyduty.domain.model.tracker.bodyComposition.MuscleMass
import com.example.heavyduty.domain.model.tracker.bodyComposition.Weight
import com.example.heavyduty.units.BodyCompositionMeasurementUnits
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddBodyCompositionViewModel
    @Inject
    constructor(
        private val addBodyCompositionRepository: AddBodyCompositionOfflineRepository
    ): ViewModel()
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
           is AddBodyCompositionEvents.SaveButtonClicked -> {
               if(_state.value.isWeightClicked && _state.value.weightValue.value != "") {
                   val weight = Weight(mass= events.weight.toDouble(), unit = BodyCompositionMeasurementUnits.MassMeasurements.Kilograms.string)
                   viewModelScope.launch {
                       addBodyCompositionRepository.insertWeight(weight = weight)
                   }
                   _state.update {
                       it.copy(
                           weightValue = mutableStateOf("")
                       )
                   }
               }

               if(_state.value.isHeightClicked && _state.value.heightValue.value != "") {
                   val height = Height(height= events.height.toDouble(), unit = BodyCompositionMeasurementUnits.HeightMeasurements.Centimeter.string)
                   viewModelScope.launch {
                       addBodyCompositionRepository.insertHeight(height = height)
                   }
                   _state.update {
                       it.copy(
                           heightValue = mutableStateOf("")
                       )
                   }
               }

               if(_state.value.isBodyFatClicked && _state.value.bodyFatValue.value != "") {
                   val bodyfat = BodyFat(bodyFat = events.bodyfat.toDouble(), unit = BodyCompositionMeasurementUnits.MassMeasurements.InPercentage.string)
                   viewModelScope.launch {
                       addBodyCompositionRepository.insertBodyFat(bodyFat = bodyfat)
                   }
                   _state.update {
                       it.copy(
                           bodyFatValue = mutableStateOf("")
                       )
                   }
               }


               if(_state.value.isMuscleMassClicked && _state.value.muscleMassValue.value != "") {
                   val muscleMass = MuscleMass(muscleMass= events.musclemass.toDouble(), unit = BodyCompositionMeasurementUnits.MassMeasurements.InPercentage.string)
                   viewModelScope.launch {
                       addBodyCompositionRepository.insertMuscleMass(muscleMass = muscleMass)
                   }
                   _state.update {
                       it.copy(
                           muscleMassValue = mutableStateOf("")
                       )
                   }
               }

           }
        }
    }
}