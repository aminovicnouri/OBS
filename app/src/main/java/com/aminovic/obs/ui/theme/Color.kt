package com.aminovic.obs.ui.theme

import android.annotation.SuppressLint
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val AccentViolet = Color(Colors.AccentViolet)

@SuppressLint("ConflictingOnColor")
val lightColors = lightColors(
    primary = AccentViolet,
    background = Color.White,
    onPrimary = Color.White,
    onBackground = Colors.DeepBlue,
    surface = Color.White,
    onSurface = Colors.DarkBlue
)

val darkColors = darkColors(
    primary = AccentViolet,
    background = Colors.DeepBlue,
    onPrimary = Color.White,
    onBackground = Color.White,
    surface = Colors.DarkBlue,
    onSurface = Color.White
)