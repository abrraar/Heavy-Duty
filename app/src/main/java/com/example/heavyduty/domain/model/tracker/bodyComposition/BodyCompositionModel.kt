package com.example.heavyduty.domain.model.tracker.bodyComposition

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.heavyduty.units.BodyCompositionMeasurementUnits
import java.text.DateFormat
import java.util.Calendar

@Entity(tableName = "body_weight")
data class Weight(
    @ColumnInfo("weight_id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String = DateFormat.getDateInstance(DateFormat.FULL).format(Calendar.getInstance().time),
    val time: String = DateFormat.getTimeInstance(DateFormat.SHORT).format(Calendar.getInstance().time),
    val mass: Double,
    val unit: BodyCompositionMeasurementUnits.MassMeasurements
)

@Entity(tableName = "body_height")
data class Height(
    @ColumnInfo("height_id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String = DateFormat.getDateInstance(DateFormat.FULL).format(Calendar.getInstance().time),
    val time: String = DateFormat.getTimeInstance(DateFormat.SHORT).format(Calendar.getInstance().time),
    val height: Double,
    val unit: BodyCompositionMeasurementUnits.HeightMeasurements
)

@Entity(tableName = "body_fat")
data class BodyFat(
    @ColumnInfo("bodyFat_id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String = DateFormat.getDateInstance(DateFormat.FULL).format(Calendar.getInstance().time),
    val time: String = DateFormat.getTimeInstance(DateFormat.SHORT).format(Calendar.getInstance().time),
    val bodyFat: Double,
    val unit: BodyCompositionMeasurementUnits.MassMeasurements
)

@Entity(tableName = "muscle_mass")
data class MuscleMass(
    @ColumnInfo("muscleMass_id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String = DateFormat.getDateInstance(DateFormat.FULL).format(Calendar.getInstance().time),
    val time: String = DateFormat.getTimeInstance(DateFormat.SHORT).format(Calendar.getInstance().time),
    val muscleMass: Double,
    val unit: BodyCompositionMeasurementUnits.MassMeasurements
)

@Entity(tableName = "bmi")
data class BMI(
    @ColumnInfo("bmi_id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String = DateFormat.getDateInstance(DateFormat.FULL).format(Calendar.getInstance().time),
    val time: String = DateFormat.getTimeInstance(DateFormat.SHORT).format(Calendar.getInstance().time),
    val bmi: Double
)


