package com.example.heavyduty.data.local.tracker.bodyComposition.main

import com.example.heavyduty.domain.model.tracker.bodyComposition.BMI
import com.example.heavyduty.domain.model.tracker.bodyComposition.BodyFat
import com.example.heavyduty.domain.model.tracker.bodyComposition.Height
import com.example.heavyduty.domain.model.tracker.bodyComposition.MuscleMass
import com.example.heavyduty.domain.model.tracker.bodyComposition.Weight
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BodyCompositionOfflineRepository
@Inject constructor(
    private val bodyCompositionDAO: BodyCompositionDAO
)
    : BodyCompositionRepository
{
    override fun getAllWeight(): Flow<List<Weight>> = bodyCompositionDAO.getAllWeight()
    override fun getAllHeight(): Flow<List<Height>> = bodyCompositionDAO.getAllHeight()
    override fun getAllBodyFat(): Flow<List<BodyFat>> = bodyCompositionDAO.getAllBodyFat()
    override fun getAllMuscleMass(): Flow<List<MuscleMass>> = bodyCompositionDAO.getAllMuscleMass()

    override fun totalNumberOfWeight(): Flow<Double> = bodyCompositionDAO.totalNumberOfWeight()
    override fun totalNumberOfHeight(): Flow<Double> = bodyCompositionDAO.totalNumberOfHeight()
    override fun totalNumberOfBodyFat(): Flow<Double> = bodyCompositionDAO.totalNumberOfBodyFat()
    override fun totalNumberOfMuscleMass(): Flow<Double> = bodyCompositionDAO.totalNumberOfMuscleMass()

    override suspend fun insertBMI(bmi: BMI) = bodyCompositionDAO.insertBMI(bmi)

}