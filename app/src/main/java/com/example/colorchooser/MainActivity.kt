
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
    val colorMap = mapOf(
        "Blue" to Color.Blue,
        "Red" to Color.Red,
        "Green" to Color.Green
    )

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
            .background(color = backgroundColor)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,

        ) {
        ColorChooserMenu(
            colorName = currentColor,
            colorsList = colorMap.keys,
            setColor = { newColor -> currentColor = newColor }
        )
        //Text(text = currentColor)
    }
}

/**
 * This is some javadoc for something
 *
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorChooserMenu(
    colorName: String,
    colorsList: Set<String>,
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
            label = { Text("Choose a color") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
                setColor("")
            }
        ) {
            colorsList.forEach { color ->
                DropdownMenuItem(
                    text = { Text(color) },
                    onClick = {
                        setColor(color)
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