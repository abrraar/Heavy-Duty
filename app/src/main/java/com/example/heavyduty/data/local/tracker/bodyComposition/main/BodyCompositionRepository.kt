package com.example.heavyduty.data.local.tracker.bodyComposition.main

import com.example.heavyduty.domain.model.tracker.bodyComposition.BMI
import com.example.heavyduty.domain.model.tracker.bodyComposition.BodyFat
import com.example.heavyduty.domain.model.tracker.bodyComposition.Height
import com.example.heavyduty.domain.model.tracker.bodyComposition.MuscleMass
import com.example.heavyduty.domain.model.tracker.bodyComposition.Weight
import kotlinx.coroutines.flow.Flow

interface BodyCompositionRepository {

    // Weight
    fun getAllWeight(): Flow<List<Weight>>
    suspend fun deleteWeight(weight: Weight)
    fun totalNumberOfWeight(): Flow<Double>

    // Height
    fun getAllHeight(): Flow<List<Height>>
    suspend fun deleteHeight(height: Height)
    fun totalNumberOfHeight(): Flow<Double>

    // Body Fat
    fun getAllBodyFat(): Flow<List<BodyFat>>
    suspend fun deleteBodyFat(bodyFat: BodyFat)
    fun totalNumberOfBodyFat(): Flow<Double>

    // Muscle Mass
    fun getAllMuscleMass(): Flow<List<MuscleMass>>
    suspend fun deleteMuscleMass(muscleMass: MuscleMass)
    fun totalNumberOfMuscleMass(): Flow<Double>




    suspend fun insertBMI(bmi: BMI)
}