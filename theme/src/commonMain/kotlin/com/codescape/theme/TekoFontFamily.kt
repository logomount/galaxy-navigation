package com.codescape.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font
import theme.Res
import theme.SpaceGrotesk_Bold
import theme.SpaceGrotesk_Light
import theme.SpaceGrotesk_Medium
import theme.SpaceGrotesk_Regular

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SpaceGroteskFamily() = FontFamily(
    Font(Res.font.SpaceGrotesk_Light, weight = FontWeight.Light),
    Font(Res.font.SpaceGrotesk_Regular, weight = FontWeight.Normal),
    Font(Res.font.SpaceGrotesk_Medium, weight = FontWeight.Medium),
    Font(Res.font.SpaceGrotesk_Regular, weight = FontWeight.SemiBold),
    Font(Res.font.SpaceGrotesk_Bold, weight = FontWeight.Bold)
)

@Composable
fun SpaceGroteskTypography() = Typography().run {
    val fontFamily = SpaceGroteskFamily()
    copy(
        displayLarge = displayLarge.copy(fontFamily = fontFamily),
        displayMedium = displayMedium.copy(fontFamily = fontFamily),
        displaySmall = displaySmall.copy(fontFamily = fontFamily),
        headlineLarge = headlineLarge.copy(fontFamily = fontFamily),
        headlineMedium = headlineMedium.copy(fontFamily = fontFamily),
        headlineSmall = headlineSmall.copy(fontFamily = fontFamily),
        titleLarge = titleLarge.copy(fontFamily = fontFamily),
        titleMedium = titleMedium.copy(fontFamily = fontFamily),
        titleSmall = titleSmall.copy(fontFamily = fontFamily),
        bodyLarge = bodyLarge.copy(fontFamily = fontFamily),
        bodyMedium = bodyMedium.copy(fontFamily = fontFamily),
        bodySmall = bodySmall.copy(fontFamily = fontFamily),
        labelLarge = labelLarge.copy(fontFamily = fontFamily),
        labelMedium = labelMedium.copy(fontFamily = fontFamily),
        labelSmall = labelSmall.copy(fontFamily = fontFamily)
    )
}