package com.example.heavyduty.data.local.tracker.bodyComposition.addBodyComposition

import com.example.heavyduty.domain.model.tracker.bodyComposition.BodyFat
import com.example.heavyduty.domain.model.tracker.bodyComposition.Height
import com.example.heavyduty.domain.model.tracker.bodyComposition.Weight
import com.example.heavyduty.domain.model.tracker.bodyComposition.MuscleMass
import javax.inject.Inject

class AddBodyCompositionOfflineRepository
    @Inject
    constructor(
        private val addBodyCompositionDAO: AddBodyCompositionDAO
    ): AddBodyCompositionRepository
{
    override suspend fun insertWeight(weight: Weight) = addBodyCompositionDAO.insertWeight(weight)

    override suspend fun insertHeight(height: Height) = addBodyCompositionDAO.insertHeight(height)

    override suspend fun insertMuscleMass(muscleMass: MuscleMass) = addBodyCompositionDAO.insertMuscleMass(muscleMass)

    override suspend fun insertBodyFat(bodyFat: BodyFat) = addBodyCompositionDAO.insertBodyFat(bodyFat)
}