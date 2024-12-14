package com.febriandi.agrojaya.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color (0xFF5ACF59),
    secondary = Color(0xFFC2F1C1),
    tertiary = Color(0xFFF1FCF1)
)

private val LightColorScheme = lightColorScheme(
    primary = Color (0xFF5ACF59),
    secondary = Color(0xFF5ACF59),
    tertiary = Color(0xFFFFFFFF),
    background = Color(0xFF5ACF59),
    surface = Color(0xFFFFFFFF),
    onPrimary = Color.White,
    onSecondary = Color (0xFF5ACF59),
    onTertiary = Color (0xFF5ACF59),
    onBackground = Color(0xFF5ACF59),
    onSurface = Color(0xFF5ACF59),
    secondaryContainer = Color (0xFFE0F9DF)

)

@Composable
fun AgroJayaTheme(
    darkTheme: Boolean = false,
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

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}