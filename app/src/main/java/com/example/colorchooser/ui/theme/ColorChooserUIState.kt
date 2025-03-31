package com.example.colorchooser

import androidx.compose.ui.graphics.Color

data class ColorChooserUiState(
    val currentColorName: String = "",
    val currentColor: Color = Color.Unspecified,
    val expanded: Boolean = false,

    )