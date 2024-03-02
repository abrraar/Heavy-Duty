package com.example.heavyduty.data.local.tracker.workoutLogbook.addCycle

import com.example.heavyduty.domain.model.tracker.workoutLogbook.Cycle

interface AddCycleRepository {

    suspend fun insertCycle(cycle: Cycle)
    suspend fun updateCycle(cycle: Cycle)
    suspend fun deleteCycle(cycle: Cycle)
}