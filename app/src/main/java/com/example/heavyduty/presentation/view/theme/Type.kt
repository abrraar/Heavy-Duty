package com.example.heavyduty.presentation.view.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.heavyduty.R

val SequelSans = FontFamily(
    Font(R.font.sequel_sans_black_body),
    Font(R.font.sequel_sans_heavy_body)
)

// Compact Screen Typography
// Compact
val CompactTypography = Typography(
    headlineLarge = TextStyle(
        fontFamily = SequelSans,
        fontSize = 32.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = SequelSans,
        fontSize = 24.sp
    ),
    titleMedium = TextStyle(
        fontFamily = SequelSans,
        fontSize = 14.sp
    ),
    labelMedium = TextStyle(
        fontFamily = SequelSans,
        fontSize = 14.sp
    )
)

// Compact Medium
val CompactMediumTypography = Typography(
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
        fontSize = 18.sp
    ),
    titleMedium =  TextStyle(
        fontFamily = SequelSans,
        fontSize = 16.sp
    ),
    titleSmall =  TextStyle(
        fontFamily = SequelSans,
        fontSize = 14.sp
    ),
    labelSmall = TextStyle(
        fontFamily = SequelSans,
        fontSize = 12.sp
    )
)

// Compact Small
val CompactSmallTypography = Typography(
    headlineLarge = TextStyle(
        fontFamily = SequelSans,
        fontSize = 34.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = SequelSans,
        fontSize = 26.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = SequelSans,
        fontSize = 18.sp
    ),
    titleMedium =  TextStyle(
        fontFamily = SequelSans,
        fontSize = 16.sp
    ),
    titleLarge = TextStyle(
        fontFamily = SequelSans,
        fontSize = 14.sp
    ),
    titleSmall =  TextStyle(
        fontFamily = SequelSans,
        fontSize = 12.sp
    ),
    labelSmall = TextStyle(
        fontFamily = SequelSans,
        fontSize = 10.sp
    )
)

// Medium Screen
val MediumTypography = Typography(
    headlineLarge = TextStyle(
        fontFamily = SequelSans,
        fontSize = 38.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = SequelSans,
        fontSize = 30.sp
    ),
    titleMedium = TextStyle(
        fontFamily = SequelSans,
        fontSize = 16.sp
    ),
    labelMedium = TextStyle(
        fontFamily = SequelSans,
        fontSize = 16.sp
    )
)

// Expanded
val ExpandedTypography = Typography(
    headlineLarge = TextStyle(
        fontFamily = SequelSans,
        fontSize = 42.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = SequelSans,
        fontSize = 34.sp
    ),
    titleMedium = TextStyle(
        fontFamily = SequelSans,
        fontSize = 18.sp
    ),
    labelMedium = TextStyle(
        fontFamily = SequelSans,
        fontSize = 18.sp
    )
)