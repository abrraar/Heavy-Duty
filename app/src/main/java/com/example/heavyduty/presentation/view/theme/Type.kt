package com.example.heavyduty.presentation.view.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.heavyduty.R

val SequelSans = FontFamily(
    Font(R.font.sequel_sans_black_body),
    Font(R.font.sequel_sans_heavy_body)
)
// Set of Material typography styles to start with
val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = SequelSans,
        fontSize = 36.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = SequelSans,
        fontSize = 28.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = SequelSans,
        fontSize = 20.sp
    ),
    titleLarge = TextStyle(
        fontFamily = SequelSans,
        fontSize = 16.sp
    ),
    titleSmall =  TextStyle(
        fontFamily = SequelSans,
        fontSize = 14.sp
    ),
    labelSmall = TextStyle(
        fontFamily = SequelSans,
        fontSize = 10.sp
    ),


)