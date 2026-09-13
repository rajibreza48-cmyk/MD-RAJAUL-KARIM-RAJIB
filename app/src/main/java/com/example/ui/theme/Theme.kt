package com.example.ui.theme

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
    primary = WarmAmberPrimaryDark,
    onPrimary = Color(0xFF451A03),
    primaryContainer = Color(0xFF7C2D12),
    onPrimaryContainer = Color(0xFFFFEDD5),
    secondary = WarmAmberLight,
    onSecondary = Color(0xFF451A03),
    secondaryContainer = Color(0xFF431407),
    onSecondaryContainer = Color(0xFFFFEDD5),
    tertiary = GoldenAccent,
    background = WarmBackgroundDark,
    surface = WarmSurfaceDark,
    surfaceVariant = WarmSurfaceVariantDark,
    onBackground = WarmOnSurfaceDark,
    onSurface = WarmOnSurfaceDark,
    onSurfaceVariant = WarmOnSurfaceVariantDark,
    outline = WarmOutlineDark
)

private val LightColorScheme = lightColorScheme(
    primary = WarmAmberPrimary,
    onPrimary = Color.White,
    primaryContainer = WarmSecondaryLight,
    onPrimaryContainer = Color(0xFF7C2D12),
    secondary = WarmSecondary,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFFFE4E6),
    onSecondaryContainer = Color(0xFF881337),
    tertiary = WarmTertiary,
    background = WarmBackgroundLight,
    surface = WarmSurfaceLight,
    surfaceVariant = WarmSurfaceVariantLight,
    onBackground = WarmOnSurfaceLight,
    onSurface = WarmOnSurfaceLight,
    onSurfaceVariant = WarmOnSurfaceVariantLight,
    outline = WarmOutlineLight
)

@Composable
fun KhabarBariTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Use our culinary palette by default for brand identity
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
