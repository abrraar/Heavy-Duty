package com.example.heavyduty.data.local.tracker.bodyComposition.main

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.heavyduty.domain.model.tracker.bodyComposition.BMI
import com.example.heavyduty.domain.model.tracker.bodyComposition.BodyFat
import com.example.heavyduty.domain.model.tracker.bodyComposition.Height
import com.example.heavyduty.domain.model.tracker.bodyComposition.MuscleMass
import com.example.heavyduty.domain.model.tracker.bodyComposition.Weight
import kotlinx.coroutines.flow.Flow

@Dao
interface BodyCompositionDAO {

//-------------------- Statement for Weight ----------------------

    @Query("SELECT * from body_weight")
    fun getAllWeight(): Flow<List<Weight>>

    @Query("SELECT COUNT(mass) FROM body_weight")
    fun totalNumberOfWeight(): Flow<Double>

//-------------------- Statement for Height -----------------------

    @Query("SELECT * from body_height")
    fun getAllHeight(): Flow<List<Height>>

    @Query("SELECT COUNT(height) FROM body_height")
    fun totalNumberOfHeight(): Flow<Double>

//------------------ Statement for Body Fat -------------------------

    @Query("SELECT * from body_fat")
    fun getAllBodyFat(): Flow<List<BodyFat>>

    @Query("SELECT COUNT(bodyfat) FROM body_fat")
    fun totalNumberOfBodyFat(): Flow<Double>

//------------------- Statement for Muscle Mass -----------------------

    @Query("SELECT * from muscle_mass")
    fun getAllMuscleMass(): Flow<List<MuscleMass>>

    @Query("SELECT COUNT(muscleMass) FROM muscle_mass")
    fun totalNumberOfMuscleMass(): Flow<Double>

//-------------------- Statement for BMI ------------------------------
    @Insert
    suspend fun insertBMI(bmi: BMI)

}