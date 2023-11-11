package com.randrez.projectmarvel.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun ProjectMarvelTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkColorScheme(
            primary = RED_A700,
            primaryContainer = RED_900,
            secondary = GRAY_300,
            background = GRAY_900,
            surface = BLACK,
            onPrimary = WHITE,
            onSecondary = BLACK,
            onBackground = GRAY_50,
            onSurface = WHITE,
            scrim = GRAY_900
        )

        else -> lightColorScheme(
            primary = RED_A700,
            primaryContainer = RED_900,
            secondary = GRAY_300,
            background = WHITE,
            surface = GRAY_50,
            onPrimary = WHITE,
            onSecondary = BLACK,
            onBackground = GRAY_900,
            onSurface = BLACK,
            scrim = GRAY_900
        )
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.scrim.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}