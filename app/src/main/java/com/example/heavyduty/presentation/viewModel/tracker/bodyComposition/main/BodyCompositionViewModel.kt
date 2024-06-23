package com.example.heavyduty.presentation.viewModel.tracker.bodyComposition.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.heavyduty.data.local.Constants
import com.example.heavyduty.data.local.Constants.BODY_COMP_LEFT_BTN
import com.example.heavyduty.data.local.Constants.BODY_COMP_RIGHT_BTN
import com.example.heavyduty.data.local.tracker.bodyComposition.main.BodyCompositionOfflineRepository
import com.example.heavyduty.units.BodyCompositionComponents
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.entry.ChartEntryModel
import com.patrykandpatrick.vico.core.entry.entryModelOf
import com.patrykandpatrick.vico.core.entry.entryOf
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class BodyCompositionViewModel
    @Inject
    constructor(
         private val bodyCompositionRepository: BodyCompositionOfflineRepository
) : ViewModel()
{
    private val _bodyCompositionState = MutableStateFlow(BodyCompositionUIState())
    val bodyCompositionState = _bodyCompositionState.asStateFlow()

    private val itemDisplayed: ArrayList<String> = arrayListOf(Constants.WEIGHT)

//--------------- Start of Graph assigning data -----------------

    fun bodyCompositionEvents(events: BodyCompositionEvents){
        when(events){
            is BodyCompositionEvents.DisplayGraph -> {
                when(events.click){
                    BODY_COMP_RIGHT_BTN -> {
                        if(itemDisplayed.contains(Constants.WEIGHT)){
                            itemDisplayed.remove(Constants.WEIGHT)
                            itemDisplayed.add(Constants.HEIGHT)
                            height()
                            _bodyCompositionState.update {
                                it.copy(
                                    rightButton = true,
                                    leftButton = true
                                )
                            }

                        }
                        else if(itemDisplayed.contains(Constants.HEIGHT)){
                            itemDisplayed.remove(Constants.HEIGHT)
                            itemDisplayed.add(Constants.BODYFAT)
                            bodyFat()

                        }
                        else if(itemDisplayed.contains(Constants.BODYFAT)){
                            itemDisplayed.remove(Constants.BODYFAT)
                            itemDisplayed.add(Constants.MUSCLEMASS)
                            muscleMass()
                            _bodyCompositionState.update {
                                it.copy(
                                    rightButton = false,
                                    leftButton = true
                                )
                            }
                        }
                    }
                    BODY_COMP_LEFT_BTN -> {
                        if(itemDisplayed.contains(Constants.MUSCLEMASS)){
                            itemDisplayed.remove(Constants.MUSCLEMASS)
                            itemDisplayed.add(Constants.BODYFAT)
                            bodyFat()
                            _bodyCompositionState.update {
                                it.copy(
                                    leftButton = true,
                                    rightButton = true
                                )
                            }
                        }
                        else if(itemDisplayed.contains(Constants.BODYFAT)){
                            itemDisplayed.remove(Constants.BODYFAT)
                            itemDisplayed.add(Constants.HEIGHT)
                            height()

                        }
                        else if(itemDisplayed.contains(Constants.HEIGHT)){
                            itemDisplayed.remove(Constants.HEIGHT)
                            itemDisplayed.add(Constants.WEIGHT)
                            weight()
                            _bodyCompositionState.update {
                                it.copy(
                                    rightButton = true,
                                    leftButton = false
                                )
                            }

                        }
                    }
                }
            }
        }
    }

//--------------- End of Graph assigning data -----------------


//-------------------------- Start of Getting BodyComposition entries to display on Graph Composable -------------------------------

    init {
        // Display weight on initial display
        weight()
    }

    private fun checkModel(array: ArrayList<Pair<String, Float>>): ChartEntryModel? {
        var model: ChartEntryModel? = chartEntryModel(array)
        if (model!!.entries[0].isEmpty()){
            model = null
        }
        return model
    }

    private fun weight() {
        val array = arrayListOf<Pair<String, Float>>()
        viewModelScope.launch {
            bodyCompositionRepository.getAllWeight().collectLatest{
                for(weight in it){
                    array.add(weight.date to weight.mass.toFloat())
                }
                val model: ChartEntryModel? = checkModel(array)
                val date = date(array)
                _bodyCompositionState.update {
                    it.copy(
                        model = model,
                        date = date,
                        title = BodyCompositionComponents.WEIGHT.component
                    )
                }
            }
        }
        viewModelScope.launch {
            bodyCompositionRepository.totalNumberOfWeight().collectLatest {
                val count = it
                _bodyCompositionState.update {
                    it.copy(
                        totalRecords = count.toInt()
                    )
                }
            }
        }
    }

    private fun height() {
        val array = arrayListOf<Pair<String, Float>>()
        viewModelScope.launch {
            bodyCompositionRepository.getAllHeight().collectLatest{
                for(weight in it){
                    array.add(weight.date to weight.height.toFloat())
                }
                val model: ChartEntryModel? = checkModel(array)
                val date =  date(array)
                _bodyCompositionState.update {
                    it.copy(
                        model = model,
                        date = date,
                        title = BodyCompositionComponents.HEIGHT.component
                    )
                }
            }
        }
        viewModelScope.launch {
            bodyCompositionRepository.totalNumberOfHeight().collectLatest {
                val count = it
                _bodyCompositionState.update {
                    it.copy(
                        totalRecords = count.toInt()
                    )
                }
            }
        }
    }

    private fun bodyFat() {

        val array = arrayListOf<Pair<String, Float>>()
        viewModelScope.launch {
            bodyCompositionRepository.getAllBodyFat().collectLatest{
                for(weight in it){
                    array.add(weight.date to weight.bodyFat.toFloat())
                }
                val model: ChartEntryModel? = checkModel(array)
                val date =  date(array)
                _bodyCompositionState.update {
                    it.copy(
                        model = model,
                        date = date,
                        title = BodyCompositionComponents.BODYFAT.component
                    )
                }
            }
        }

        viewModelScope.launch {
            bodyCompositionRepository.totalNumberOfBodyFat().collectLatest {
                val count = it
                _bodyCompositionState.update {
                    it.copy(
                        totalRecords = count.toInt()
                    )
                }
            }
        }
    }

    private fun muscleMass() {

        val array = arrayListOf<Pair<String, Float>>()
        viewModelScope.launch {
            bodyCompositionRepository.getAllMuscleMass().collectLatest{
                for(weight in it){
                    array.add(weight.date to weight.muscleMass.toFloat())
                }
                val model: ChartEntryModel? = checkModel(array)
                val date =  date(array)
                _bodyCompositionState.update {
                    it.copy(
                        model = model,
                        date = date,
                        title = BodyCompositionComponents.MUSCLEMASS.component
                    )
                }
            }
        }
        viewModelScope.launch {
            bodyCompositionRepository.totalNumberOfMuscleMass().collectLatest {
                val count = it
                _bodyCompositionState.update {
                    it.copy(
                        totalRecords = count.toInt()
                    )
                }
            }
        }
    }
//-------------------------- End of Getting BodyComposition entries to display on Graph Composable -------------------------------

//--------------------------- Start of chart Model and get X-axis in date format ------------------------------------------
    private fun chartEntryModel(data: ArrayList<Pair<String, Float>>): ChartEntryModel
    {
        val yData = data.associate{ (dateString, yValue) -> LocalDate.parse(dateString) to yValue }
        val xValuesToDates = yData.keys.associateBy { it.toEpochDay().toFloat() }
        return entryModelOf(xValuesToDates.keys.zip(other = yData.values,transform = ::entryOf))
    }

    private fun date(data: ArrayList<Pair<String, Float>>): AxisValueFormatter<AxisPosition.Horizontal.Bottom>
    {
        val yData = data.associate{ (dateString, yValue) -> LocalDate.parse(dateString) to yValue }
        val xValuesToDates = yData.keys.associateBy { it.toEpochDay().toFloat() }
        val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("d MMMM yy")
        return AxisValueFormatter{ value, _ -> (xValuesToDates[value] ?: LocalDate.ofEpochDay(value.toLong())).format(dateTimeFormatter) }
    }
}
//--------------------------- End of chart Model and get X-axis in date format ------------------------------------------