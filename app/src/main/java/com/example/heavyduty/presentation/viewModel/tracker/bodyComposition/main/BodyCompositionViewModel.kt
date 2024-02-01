package com.example.heavyduty.presentation.viewModel.tracker.bodyComposition.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.heavyduty.data.local.tracker.bodyComposition.main.BodyCompositionOfflineRepository
import com.example.heavyduty.data.local.tracker.bodyComposition.main.BodyCompositionRepository
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.entry.ChartEntryModel
import com.patrykandpatrick.vico.core.entry.entryModelOf
import com.patrykandpatrick.vico.core.entry.entryOf
import com.patrykandpatrick.vico.core.extension.setFieldValue
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
    private val _state = MutableStateFlow(BodyCompositionUIState())
    val state = _state.asStateFlow()
    private val data: MutableList<Pair<String, Float>> = ArrayList()

//--------------- Start of Graph assigning data -----------------

    fun onBodyCompositionEvents(event: BodyCompositionEvents){
        when(event){
            is BodyCompositionEvents.displayGraph ->
                when(event.pagerState){
                    0 -> _state.update {
                        data.clear()
                        it.copy(
                            model = chartEntryModel(weight()),
                            date = date(weight())
                        )
                    }
                    1 -> _state.update {
                        data.clear()
                        it.copy(
                            model = chartEntryModel(height()),
                            date = date(height())
                        )
                    }
                    2 -> _state.update {
                        data.clear()
                        it.copy(
                            model = chartEntryModel(bodyfat()),
                            date = date(bodyfat())
                        )
                    }
                    3 -> _state.update {
                        data.clear()
                        it.copy(
                            model = chartEntryModel(muscleMass()),
                            date = date(weight())
                        )
                    }
                }
        }
    }

//--------------- End of Graph assigning data -----------------


//-------------------------- Start of Getting BodyComposition entries to display on Graph Composable -------------------------------

    private fun weight(): MutableList<Pair<String, Float>>{
        viewModelScope.launch {
            bodyCompositionRepository.getAllWeight().collectLatest{
                for(weight in it){
                    data.add(weight.date to weight.mass.toFloat())
                }
            }
        }
        return data
    }

    private fun height(): MutableList<Pair<String, Float>>{

        viewModelScope.launch {
            bodyCompositionRepository.getAllHeight().collectLatest{

                for(weight in it){
                    data.add(weight.date to weight.height.toFloat())
                }

            }
        }
        return data
    }


    private fun bodyfat(): MutableList<Pair<String, Float>>{

        viewModelScope.launch {
            bodyCompositionRepository.getAllWeight().collectLatest{

                for(weight in it){
                    data.add(weight.date to weight.mass.toFloat())
                }
            }
        }
        return data
    }

    private fun muscleMass(): MutableList<Pair<String, Float>>{

        viewModelScope.launch {
            bodyCompositionRepository.getAllMuscleMass().collectLatest{

                for(weight in it){
                    data.add(weight.date to weight.muscleMass.toFloat())
                }
            }
        }
        return data
    }
//-------------------------- End of Getting BodyComposition entries to display on Graph Composable -------------------------------

//--------------------------- Start of chart Model and get X-axis in date format ------------------------------------------
    private fun chartEntryModel(data: MutableList<Pair<String, Float>>): ChartEntryModel
    {
        val yData = data.associate{ (dateString, yValue) -> LocalDate.parse(dateString) to yValue }
        val xValuesToDates = yData.keys.associateBy { it.toEpochDay().toFloat() }
        return entryModelOf(xValuesToDates.keys.zip(other = yData.values,transform = ::entryOf))
    }

    private fun date(data: MutableList<Pair<String, Float>>): AxisValueFormatter<AxisPosition.Horizontal.Bottom>
    {
        val yData = data.associate{ (dateString, yValue) -> LocalDate.parse(dateString) to yValue }
        val xValuesToDates = yData.keys.associateBy { it.toEpochDay().toFloat() }
        val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("d MMMM yy")
        return AxisValueFormatter{ value, _ -> (xValuesToDates[value] ?: LocalDate.ofEpochDay(value.toLong())).format(dateTimeFormatter) }
    }
}
//--------------------------- End of chart Model and get X-axis in date format ------------------------------------------