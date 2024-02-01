package com.example.heavyduty.presentation.viewModel.tracker

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TrackerViewModel
@Inject
constructor(): ViewModel()
{
    private val _state = MutableStateFlow(TrackerUIState())
    val state = _state.asStateFlow()

    fun onEvent(events: TrackerEvents) {
        when(events){
            is TrackerEvents.TabClick ->
            {
                if(events.tabPosition == 0){
                    _state.update {
                        it.copy(selectedItemIndex = 1)
                    }
                }
                if (events.tabPosition == 1){
                    _state.update {
                        it.copy(selectedItemIndex = 0)
                    }
                }

            }
        }
    }

}