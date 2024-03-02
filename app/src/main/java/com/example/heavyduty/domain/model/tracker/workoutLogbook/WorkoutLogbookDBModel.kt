package com.example.heavyduty.domain.model.tracker.workoutLogbook

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.heavyduty.units.ExerciseType
import com.example.heavyduty.units.IntensityUnits
import com.example.heavyduty.units.Muscles
import java.util.Date

@Entity(tableName = "cycle")
    data class Cycle(
        @ColumnInfo("cycle_id")
        @PrimaryKey(autoGenerate = true)
        val cycleID: Int = 0,
        @ColumnInfo("cycle_model")
        val cycleModel: CycleModel
    )



