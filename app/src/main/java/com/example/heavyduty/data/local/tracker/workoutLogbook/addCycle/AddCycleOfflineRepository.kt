package com.example.heavyduty.data.local.tracker.workoutLogbook.addCycle

import com.example.heavyduty.domain.model.tracker.workoutLogbook.Cycle
import javax.inject.Inject

class AddCycleOfflineRepository
@Inject constructor(
    private val addCycleDAO: AddCycleDAO
) : AddCycleRepository
{
    override suspend fun insertCycle(cycle: Cycle) = addCycleDAO.insertCycle(cycle)
    override suspend fun updateCycle(cycle: Cycle) = addCycleDAO.updateCycle(cycle)
    override suspend fun deleteCycle(cycle: Cycle) = addCycleDAO.deleteCycle(cycle)
}