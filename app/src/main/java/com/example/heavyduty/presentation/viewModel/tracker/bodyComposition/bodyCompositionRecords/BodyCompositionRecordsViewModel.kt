package com.example.heavyduty.presentation.viewModel.tracker.bodyComposition.bodyCompositionRecords

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.heavyduty.data.local.tracker.bodyComposition.main.BodyCompositionOfflineRepository
import com.example.heavyduty.domain.model.tracker.bodyComposition.BodyFat
import com.example.heavyduty.domain.model.tracker.bodyComposition.Height
import com.example.heavyduty.domain.model.tracker.bodyComposition.MuscleMass
import com.example.heavyduty.domain.model.tracker.bodyComposition.Weight
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BodyCompositionRecordsViewModel
@Inject
constructor(
    private val bodyCompositionOfflineRepository: BodyCompositionOfflineRepository
): ViewModel()
{
    private val _bodyCompositionRecords = MutableStateFlow(BodyCompositionRecordsUIState())
    val bodyCompositionRecordsRecords = _bodyCompositionRecords.asStateFlow()

    private val recordList: ArrayList<Any> = arrayListOf()

    init {
        updateUIState()
    }

    private fun updateUIState(){
        // Weight
        viewModelScope.launch {
            val weightList: ArrayList<Weight> = arrayListOf()
            bodyCompositionOfflineRepository.getAllWeight().collectLatest {
                for(weight in it){
                    weightList.add(weight)
                }
                recordList.addAll(weightList)
                _bodyCompositionRecords.update {
                    it.copy(
                        recordList = recordList,
                        weightList = weightList
                    )
                }
            }
        }

        // Height
        viewModelScope.launch {
            val heightList: ArrayList<Height> = arrayListOf()
            bodyCompositionOfflineRepository.getAllHeight().collectLatest {
                for(height in it){
                    heightList.add(height)
                }
                recordList.addAll(heightList)
                _bodyCompositionRecords.update {
                    it.copy(
                        recordList = recordList,
                        heightList = heightList
                    )
                }
            }
        }

        // BodyFat
        viewModelScope.launch {
            val bodyFatList: ArrayList<BodyFat> = arrayListOf()
            bodyCompositionOfflineRepository.getAllBodyFat().collectLatest {
                for(bodyfat in it){
                    bodyFatList.add(bodyfat)
                }
                recordList.addAll(bodyFatList)
                _bodyCompositionRecords.update {
                    it.copy(
                        bodyFatList = bodyFatList,
                        recordList = recordList
                    )
                }
            }
        }

        // Muscle Mass
        viewModelScope.launch {
            val muscleMassList: ArrayList<MuscleMass> = arrayListOf()
            bodyCompositionOfflineRepository.getAllMuscleMass().collectLatest {
                for(musclemass in it){
                    muscleMassList.add(musclemass)
                }
                recordList.addAll(muscleMassList)
                _bodyCompositionRecords.update {
                    it.copy(
                        musclemassList = muscleMassList,
                        recordList = recordList
                    )
                }
            }
        }
    }
}