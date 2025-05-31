package com.example.c2h5oh.theme


import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200
)

@Composable
fun C2h5ohTheme(
    useDarkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colors = darkColorScheme(
        background = Color.Black,
        surface = Color.Black,
        onBackground = Color.White,
        onSurface = Color.White
        // 추가로 primary, secondary 등도 원하면 설정 가능
    )

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}