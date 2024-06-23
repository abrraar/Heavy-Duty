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
    // Weight
    override fun getAllWeight(): Flow<List<Weight>> = bodyCompositionDAO.getAllWeight()
    override suspend fun deleteWeight(weight: Weight) = bodyCompositionDAO.deleteWeight(weight)
    override fun totalNumberOfWeight(): Flow<Double> = bodyCompositionDAO.totalNumberOfWeight()

    // Height
    override fun getAllHeight(): Flow<List<Height>> = bodyCompositionDAO.getAllHeight()
    override suspend fun deleteHeight(height: Height) = bodyCompositionDAO.deleteHeight(height)
    override fun totalNumberOfHeight(): Flow<Double> = bodyCompositionDAO.totalNumberOfHeight()

    // BodyFat
    override fun getAllBodyFat(): Flow<List<BodyFat>> = bodyCompositionDAO.getAllBodyFat()
    override suspend fun deleteBodyFat(bodyFat: BodyFat) = bodyCompositionDAO.deleteBodyFat(bodyFat)
    override fun totalNumberOfBodyFat(): Flow<Double> = bodyCompositionDAO.totalNumberOfBodyFat()

    // MuscleMass
    override fun getAllMuscleMass(): Flow<List<MuscleMass>> = bodyCompositionDAO.getAllMuscleMass()
    override suspend fun deleteMuscleMass(muscleMass: MuscleMass) = bodyCompositionDAO.deleteMuscleMass(muscleMass)
    override fun totalNumberOfMuscleMass(): Flow<Double> = bodyCompositionDAO.totalNumberOfMuscleMass()

    // BMI
    override suspend fun insertBMI(bmi: BMI) = bodyCompositionDAO.insertBMI(bmi)

}