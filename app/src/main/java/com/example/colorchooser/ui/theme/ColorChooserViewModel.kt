package com.example.colorchooser

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.colorchooser.data.DataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ColorChooserViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ColorChooserUiState())
    val uiState = _uiState.asStateFlow()

    fun setExpanded(newExpanded: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(expanded = newExpanded)
        }
    }

    fun setColorName(newColorName: String) {
        _uiState.update { currentState ->
            currentState.copy(
                currentColorName = newColorName,
                currentColor = DataSource.colorMap.getOrDefault(newColorName, Color.Unspecified)
            )
        }
    }


    fun setColor(colorName: String) {
        setColor(DataSource.colorMap.getOrDefault(colorName, _uiState.value.currentColor))
    }

    private fun setColor(newColor: Color) {
        _uiState.update { currentState ->
            currentState.copy(
                currentColor =  newColor,
            )
        }
    }

    fun setRed(red: Float) {
        val current = _uiState.value.currentColor
        val newColor = Color(red, current.green, current.blue)
        setColor(newColor)
    }
    fun setBlue(blue: Float) {
        val current = _uiState.value.currentColor
        val newColor = Color(current.red, current.green, blue)
        setColor(newColor)
    }
    fun setGreen(green: Float) {
        val current = _uiState.value.currentColor
        val newColor = Color(current.red, green, current.blue)
        setColor(newColor)
    }
}