package com.example.colorpicker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.colorchooser.ui.theme.ColorChooserTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ColorChooserTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ColorChooserApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ColorChooserApp(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        ColorChooserMenu("Blue")
        Text(text= "Blue")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorChooserMenu(
    colorName: String,
    modifier: Modifier = Modifier
) {
    ExposedDropdownMenuBox(
        expanded = false,
        onExpandedChange = {}
    ) {
        OutlinedTextField(
            value = colorName,
            readOnly = true,
            onValueChange = {}
        )

    }
}

@Preview(showBackground = true)
@Composable
fun ColorPickerPreview() {
    ColorChooserTheme {
        ColorChooserApp(
            Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center))
    }
}