package com.example.heavyduty.data.local.tracker.bodyComposition.main

import com.example.heavyduty.domain.model.tracker.bodyComposition.BMI
import com.example.heavyduty.domain.model.tracker.bodyComposition.BodyFat
import com.example.heavyduty.domain.model.tracker.bodyComposition.Height
import com.example.heavyduty.domain.model.tracker.bodyComposition.MuscleMass
import com.example.heavyduty.domain.model.tracker.bodyComposition.Weight
import kotlinx.coroutines.flow.Flow

interface BodyCompositionRepository {

    fun getAllWeight(): Flow<List<Weight>>
    fun getAllHeight(): Flow<List<Height>>
    fun getAllBodyFat(): Flow<List<BodyFat>>
    fun getAllMuscleMass(): Flow<List<MuscleMass>>

    fun totalNumberOfWeight(): Flow<Double>
    fun totalNumberOfHeight(): Flow<Double>
    fun totalNumberOfBodyFat(): Flow<Double>
    fun totalNumberOfMuscleMass(): Flow<Double>

    suspend fun insertBMI(bmi: BMI)
}