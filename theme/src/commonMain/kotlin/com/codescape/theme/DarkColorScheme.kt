package com.codescape.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF6A79FF), // A bright blue for buttons
    onPrimary = Color.White,
    background = Color(0xFF111118),
    onBackground = Color.White,
    surface = Color(0xFF1E1E1E), // Slightly lighter for cards/surfaces
    onSurface = Color.White,
    secondaryContainer = Color(0xFF2A2A2A) // For selected items or card backgrounds
)

@Composable
fun GalaxyNavigationTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = SpaceGroteskTypography(),
        content = content
    )
}