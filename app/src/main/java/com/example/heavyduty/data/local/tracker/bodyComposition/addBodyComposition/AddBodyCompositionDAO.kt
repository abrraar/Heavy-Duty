package com.example.heavyduty.data.local.tracker.bodyComposition.addBodyComposition

import androidx.room.Dao
import androidx.room.Insert
import com.example.heavyduty.domain.model.tracker.bodyComposition.BodyFat
import com.example.heavyduty.domain.model.tracker.bodyComposition.Height
import com.example.heavyduty.domain.model.tracker.bodyComposition.MuscleMass
import com.example.heavyduty.domain.model.tracker.bodyComposition.Weight

@Dao
interface AddBodyCompositionDAO {
    @Insert
    suspend fun insertWeight(weight: Weight)

    @Insert
    suspend fun insertHeight(height: Height)

    @Insert
    suspend fun insertMuscleMass(muscleMass: MuscleMass)

    @Insert
    suspend fun insertBodyFat(bodyFat: BodyFat)

}