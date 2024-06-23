package com.example.heavyduty.domain.model.tracker.bodyComposition

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.heavyduty.units.BodyCompositionMeasurementUnits
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

//val date: String = DateFormat.getTimeInstance(DateFormat.SHORT).format(Calendar.getInstance().time)

@Entity(tableName = "body_weight")
data class Weight(
    @ColumnInfo("weight_id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String = SimpleDateFormat("yyyy-MM-dd").format(Date()),
    val time: String = DateFormat.getTimeInstance(DateFormat.SHORT).format(Calendar.getInstance().time),
    val mass: Double,
    val unit: String
)

@Entity(tableName = "body_height")
data class Height(
    @ColumnInfo("height_id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String = SimpleDateFormat("yyyy-MM-dd").format(Date()),
    val time: String = DateFormat.getTimeInstance(DateFormat.SHORT).format(Calendar.getInstance().time),
    val height: Double,
    val unit: String
)

@Entity(tableName = "body_fat")
data class BodyFat(
    @ColumnInfo("bodyFat_id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String = SimpleDateFormat("yyyy-MM-dd").format(Date()),
    val time: String = DateFormat.getTimeInstance(DateFormat.SHORT).format(Calendar.getInstance().time),
    val bodyFat: Double,
    val unit: String
)


@Entity(tableName = "muscle_mass")
data class MuscleMass(
    @ColumnInfo("muscleMass_id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String = SimpleDateFormat("yyyy-MM-dd").format(Date()),
    val time: String = DateFormat.getTimeInstance(DateFormat.SHORT).format(Calendar.getInstance().time),
    val muscleMass: Double,
    val unit: String
)

@Entity(tableName = "bmi")
data class BMI(
    @ColumnInfo("bmi_id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String = SimpleDateFormat("yyyy-MM-dd").format(Date()),
    val time: String = DateFormat.getTimeInstance(DateFormat.SHORT).format(Calendar.getInstance().time),
    val bmi: Double
)


