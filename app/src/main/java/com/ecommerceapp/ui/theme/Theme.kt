package com.ecommerceapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color(0xFFEE1A0A),
    primaryVariant = Purple700,
    secondary = Teal200,
    background = AppBg,
    onPrimary = Color.White,
    onSurface = Color(0xFFEE1A0A),
    onBackground = Color(0xFFEE1A0A)
)

private val LightColorPalette = lightColors(
    primary = Color(0xFFEE1A0A),
    primaryVariant = Purple700,
    secondary = Teal200,
    background = AppBg,
    onPrimary = Color.White,//effects button text color
    onSurface = Color(0xFFEE1A0A),//effects outlinetextfield
    onBackground = Color(0xFFEE1A0A)//effects text color
    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun EcommerceAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}