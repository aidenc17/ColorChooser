
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

    val colorsList = listOf("Blue", "Red", "Green")
    var currentColor by remember { mutableStateOf("") }

    val backgroundColor = when (currentColor) {
        "Blue" -> Color.Blue
        "Red" -> Color.Red
        "Green" -> Color.Green
        else -> Color.Gray
    }

    Column(
        modifier = modifier
            .background(backgroundColor)
            .fillMaxWidth()
    ) {
        ColorChooserMenu(
            colorName= currentColor,
            colorsList = colorsList,
            setColor = {newColor -> currentColor = newColor}
        )
        Text(text= currentColor)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorChooserMenu(
    colorName: String,
    colorsList: List<String>,
    setColor: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }


    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
            .background(color = Color.LightGray)
    ) {
        OutlinedTextField(
            value = colorName,
            readOnly = true,
            onValueChange = {},
            label = { Text("Chose a Color") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {expanded = false}
        ) {
            colorsList.forEach() { color ->
                DropdownMenuItem(
                    text = { Text(color) },
                    onClick = {

                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ColorPickerPreview() {
    ColorChooserTheme {
        ColorChooserApp(
            Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    }
}