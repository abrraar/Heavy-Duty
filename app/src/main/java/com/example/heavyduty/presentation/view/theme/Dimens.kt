package com.example.heavyduty.presentation.view.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.heavyduty.domain.model.tracker.bodyComposition.Height

data class Dimens(
    val extraSmall: Dp = 0.dp,
    val small1: Dp = 0.dp,
    val small2: Dp = 0.dp,
    val small3: Dp = 0.dp,
    val medium1: Dp = 0.dp,
    val medium2: Dp = 0.dp,
    val medium3: Dp = 0.dp,
    val large1: Dp = 0.dp,
    val large2: Dp = 0.dp,
    val large3: Dp = 0.dp,

    // Graph
    val graphHeight: Dp = 250.dp,

    // Card
    val cardHeight: Dp = 220.dp,
    val cardWidth: Dp = 350.dp,
    val cardTextFieldHeight: Dp = 80.dp,
    val cardTextFieldWidth: Dp = 280.dp,

    // Button
    val smallButtonHeight: Dp = 40.dp,
    val smallButtonWidth: Dp = 40.dp,

    val mediumButtonHeight: Dp = 40.dp,
    val mediumButtonWidth: Dp = 40.dp,

    val largeButtonHeight: Dp = 40.dp,
    val largeButtonWidth: Dp = 40.dp,

    // Prompt
    val promptHeight: Dp = 200.dp,
    val promptWidth: Dp = 300.dp,
    val promptBtnHeight: Dp = 60.dp,
    val promptBtnWidth: Dp = 150.dp,

    val logoSize: Dp = 42.dp
)

val CompactSmallDimens = Dimens(
    small1 = 6.dp,
    small2 = 5.dp,
    small3 = 8.dp,
    medium1 = 15.dp,
    medium2 = 26.dp,
    medium3 = 30.dp,
    large1 = 40.dp,
    large2 = 45.dp,
    large3 = 50.dp,

    graphHeight = 220.dp,

    cardHeight = 200.dp,
    cardWidth = 300.dp,
    cardTextFieldWidth = 250.dp,

    smallButtonWidth = 140.dp,
    smallButtonHeight = 45.dp,
    mediumButtonWidth = 240.dp,
    mediumButtonHeight = 45.dp,
    largeButtonWidth = 280.dp,
    largeButtonHeight = 45.dp,

    promptHeight = 200.dp,
    promptWidth = 280.dp,
    promptBtnHeight = 60.dp,
    promptBtnWidth = 140.dp,

    logoSize = 36.dp
)

val CompactMediumDimens = Dimens(
    small1 = 8.dp,
    small2 = 13.dp,
    small3 = 17.dp,
    medium1 = 25.dp,
    medium2 = 30.dp,
    medium3 = 35.dp,
    large1 = 50.dp,
    large2 = 55.dp,
    large3 = 65.dp,

    graphHeight = 250.dp,

    cardHeight = 230.dp,
    cardWidth = 350.dp,

    smallButtonWidth = 50.dp,
    smallButtonHeight = 150.dp,
    mediumButtonWidth = 250.dp,
    mediumButtonHeight = 50.dp,
    largeButtonHeight = 50.dp,
    largeButtonWidth = 300.dp,

    promptHeight = 200.dp,
    promptWidth = 300.dp,
    promptBtnHeight = 60.dp,
    promptBtnWidth = 150.dp,
)

val CompactDimens = Dimens(
    small1 = 10.dp,
    small2 = 15.dp,
    small3 = 20.dp,
    medium1 = 30.dp,
    medium2 = 36.dp,
    medium3 = 40.dp,
    large1 = 75.dp,
    large2 = 80.dp
)

val MediumDimens = Dimens(
    small1 = 10.dp,
    small2 = 15.dp,
    small3 = 20.dp,
    medium1 = 30.dp,
    medium2 = 36.dp,
    medium3 = 40.dp,
    large1 = 100.dp,
    large2 = 110.dp,
    logoSize = 55.dp
)

val ExpandedDimens = Dimens(
    small1 = 15.dp,
    small2 = 20.dp,
    small3 = 25.dp,
    medium1 = 35.dp,
    medium2 = 30.dp,
    medium3 = 45.dp,
    large1 = 110.dp,
    large2 = 130.dp,
    logoSize = 72.dp
)