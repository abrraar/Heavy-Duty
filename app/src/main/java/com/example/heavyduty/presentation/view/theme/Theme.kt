package com.example.heavyduty.presentation.view.theme

import android.app.Activity
import android.bluetooth.BluetoothClass.Device
import android.os.Build
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = MainWMM,
    onPrimary = White,

    secondary = Black,
    onSecondary = White,

)

private val LightColorScheme = lightColorScheme(
    primary = MainHIT,
    onPrimary = White,

    secondary = Black,
    onSecondary = White,

    tertiary = RecycleItemColor,
    onTertiary = White,

    background = ScreenBackgroundColor,
    onBackground = onScreenBackgroundColor,

    error = Red,
    onError = White

)

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun HeavyDutyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    val screenSize: Int = Devices.PIXEL.length
    Log.i("PIXEL", screenSize.toString())

    val activity: Activity = LocalContext.current as Activity
    val windowSize = calculateWindowSizeClass(activity = activity)

    val config = LocalConfiguration.current
    var typography = CompactTypography
    var appDimens = CompactDimens

    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            if (config.screenWidthDp <= 360 )
            {
                appDimens = CompactSmallDimens
                typography = CompactSmallTypography
            }
            else if (config.screenWidthDp < 599)
            {
                appDimens = CompactMediumDimens
                typography = CompactMediumTypography
            }
            else
            {
                appDimens = CompactDimens
                typography = CompactTypography
            }
        }

        WindowWidthSizeClass.Medium -> {
            appDimens = MediumDimens
            typography = MediumTypography
        }

        WindowWidthSizeClass.Expanded -> {
            appDimens = ExpandedDimens
            typography = ExpandedTypography
        }
    }

    ProvideAppUtils(appDimens = appDimens) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography,
            content = content
        )
    }
}

val MaterialTheme.dimens
    @Composable
    get() = LocalAppDimens.current