package com.vanyscore.app.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

private val YellowColorScheme = lightColorScheme(
    primary = Amber500,
    onPrimary = DarkAmber,
    primaryContainer = Amber100,
    onPrimaryContainer = DeepBrown,
    secondary = Brown500,
    onSecondary = White,
    secondaryContainer = Brown200,
    onSecondaryContainer = Color(0xFF3E2723),
    tertiary = Orange500,
    onTertiary = Color(0xFF3E2723),
    tertiaryContainer = Orange200,
    onTertiaryContainer = Color(0xFF4E342E),
    background = LightYellow,
    onBackground = DeepBrown,
    surface = White,
    onSurface = Color(0xFF4E342E),
    error = ErrorRed,
    onError = White,
    outline = MutedBrown,
    surfaceVariant = Color(0xFFEAEAEA),
    inverseOnSurface = White,
    onErrorContainer = White,
    onSurfaceVariant = DeepBrown,
    errorContainer = White,
    inversePrimary = Color(0xFFFFA000),
    inverseSurface = Color(0xFFEEEEEE),
    outlineVariant = MutedBrown,
    scrim = Color(0x99000000),
    surfaceTint = Amber500,
)

enum class AppTheme {
    LIGHT,
    DARK,
    YELLOW_LIGHT,
}

@Composable
fun TasksTheme(
    theme: AppTheme = AppTheme.LIGHT,
    content: @Composable () -> Unit
) {
    val colorScheme = when(theme) {
        AppTheme.DARK -> DarkColorScheme
        AppTheme.LIGHT -> LightColorScheme
        AppTheme.YELLOW_LIGHT -> YellowColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}