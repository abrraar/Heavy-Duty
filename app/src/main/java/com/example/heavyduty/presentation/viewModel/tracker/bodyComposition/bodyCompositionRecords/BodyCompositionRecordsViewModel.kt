package com.example.heavyduty.presentation.viewModel.tracker.bodyComposition.bodyCompositionRecords

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.collectAsState
import kotlin.Pair
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.heavyduty.data.local.Constants.CANCEL
import com.example.heavyduty.data.local.Constants.CONFIRM
import com.example.heavyduty.data.local.Constants.PROMPT
import com.example.heavyduty.data.local.tracker.bodyComposition.main.BodyCompositionOfflineRepository
import com.example.heavyduty.domain.model.tracker.bodyComposition.BodyFat
import com.example.heavyduty.domain.model.tracker.bodyComposition.Height
import com.example.heavyduty.domain.model.tracker.bodyComposition.MuscleMass
import com.example.heavyduty.domain.model.tracker.bodyComposition.Weight
import com.example.heavyduty.units.BodyCompositionComponents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BodyCompositionRecordsViewModel
@Inject
constructor(
    private val bodyCompositionOfflineRepository: BodyCompositionOfflineRepository,
): ViewModel()
{
    private val _bodyCompositionRecords = MutableStateFlow(BodyCompositionRecordsUIState())
    val bodyCompositionRecordsRecords = _bodyCompositionRecords.asStateFlow()

    private var bodyfatList = listOf<Any>()
    private var muscleList = listOf<Any>()
    private var heightList = listOf<Any>()
    private var weightist = listOf<Any>()

    private var recordList = listOf<Any>()

    init {
        Log.i("update triggered", "suwi")
        updateUIState()
    }

    private fun updateUIState(){
        // Muscle Mass
        viewModelScope.launch {
            bodyCompositionOfflineRepository.getAllBodyFat().collectLatest { bodyfat ->
                bodyfatList = bodyfat
                bodyCompositionOfflineRepository.getAllMuscleMass().collectLatest { muscle ->
                    muscleList = muscle
                    bodyCompositionOfflineRepository.getAllHeight().collectLatest { height ->
                        heightList = height
                        bodyCompositionOfflineRepository.getAllWeight().collectLatest { weight ->
                            weightist = weight
                            recordList = weight + height + muscle + bodyfat
                            _bodyCompositionRecords.update {
                                it.copy(
                                    recordList = recordList,
                                    weightList = weightist,
                                    heightList = heightList,
                                    musclemassList = muscleList,
                                    bodyfatList = bodyfatList
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    fun bodyCompositionRecordsEvents(events: BodyCompositionRecordsEvents){
        when(events){
            is BodyCompositionRecordsEvents.RecordDeleteClicked -> {
                when(events.status){
                    PROMPT -> {
                        // Prompt header
                        when(events.item){
                            is Weight -> {
                                _bodyCompositionRecords.update {
                                    it.copy(
                                        deleteTitleText = BodyCompositionComponents.WEIGHT.component
                                    )
                                }
                            }
                            is Height -> {
                                _bodyCompositionRecords.update {
                                    it.copy(
                                        deleteTitleText = BodyCompositionComponents.HEIGHT.component
                                    )
                                }
                            }
                            is BodyFat -> {
                                _bodyCompositionRecords.update {
                                    it.copy(
                                        deleteTitleText = BodyCompositionComponents.BODYFAT.component
                                    )
                                }
                            }
                            is MuscleMass -> {
                                _bodyCompositionRecords.update {
                                    it.copy(
                                        deleteTitleText = BodyCompositionComponents.MUSCLEMASS.component
                                    )
                                }
                            }
                        }
                        // Display prompt and get item
                        _bodyCompositionRecords.update {
                            it.copy(
                                recordDelete = true,
                                item = events.item
                            )
                        }
                    }
                    CONFIRM -> {
                        deleteRecord(_bodyCompositionRecords.value.item!!)
                        updateUIState()
                        _bodyCompositionRecords.update {
                            it.copy(
                                recordDelete = false
                            )
                        }
                    }
                    CANCEL -> {
                        // Close prompt
                        _bodyCompositionRecords.update {
                            it.copy(
                                recordDelete = false
                            )
                        }
                    }
                }
            }
        }
    }

    private fun deleteRecord(any: Any){
        when(any){
            is Weight -> {
                viewModelScope.launch {
                    bodyCompositionOfflineRepository.deleteWeight(any)
                }
            }
            is Height -> {
                viewModelScope.launch {
                    bodyCompositionOfflineRepository.deleteHeight(any)
                }
            }
            is BodyFat -> {
                viewModelScope.launch {
                    bodyCompositionOfflineRepository.deleteBodyFat(any)
                }
            }
            is MuscleMass -> {
                viewModelScope.launch {
                    bodyCompositionOfflineRepository.deleteMuscleMass(any)
                }
            }
        }
    }
}