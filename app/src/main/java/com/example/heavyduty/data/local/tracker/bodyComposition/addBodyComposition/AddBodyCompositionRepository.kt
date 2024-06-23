package com.example.heavyduty.data.local.tracker.bodyComposition.addBodyComposition

import com.example.heavyduty.domain.model.tracker.bodyComposition.BodyFat
import com.example.heavyduty.domain.model.tracker.bodyComposition.Height
import com.example.heavyduty.domain.model.tracker.bodyComposition.MuscleMass
import com.example.heavyduty.domain.model.tracker.bodyComposition.Weight

interface AddBodyCompositionRepository {

    suspend fun insertWeight(weight: Weight)

    suspend fun insertHeight(height: Height)

    suspend fun insertMuscleMass(muscleMass: MuscleMass)

    suspend fun insertBodyFat(bodyFat: BodyFat)
}