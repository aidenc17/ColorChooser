
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
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.colorchooser.ColorChooserViewModel
import com.example.colorchooser.data.DataSource
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
    colorChooserViewModel: ColorChooserViewModel = viewModel()
) {
    val colorMap = mapOf(
        "Blue" to Color.Blue,
        "Red" to Color.Red,
        "Green" to Color.Green,
        "Magenta" to Color.Magenta
    )
    //val colorsList = listOf("Blue", "Red", "Green")
    var currentColor by remember { mutableStateOf("") }

//    val backgroundColor = when (currentColor) {
//        "Blue" -> Color.Blue
//        "Red" -> Color.Red
//        "Green" -> Color.Green
//        else -> Color.Gray
//    }

    val colorChooserUIState = colorChooserViewModel.uiState.collectAsState()


    Column(
        modifier = modifier
            .background(color = colorChooserUIState.value.currentColor)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,

        ) {
        ColorChooserMenu(
            colorName = colorChooserUIState.value.currentColorName,
            colorsList = DataSource.colorMap.keys,
            setColor = { newColor -> colorChooserViewModel.setColorName(newColor) },
            expanded = colorChooserUIState.value.expanded,
            setExpanded = { colorChooserViewModel.setExpanded(it) }
        )
        //Text(text = currentColor)
        Slider(
            value = colorChooserUIState.value.currentColor.red,
            onValueChange = { colorChooserViewModel.setRed(it) }
        )
        Slider(
            value = colorChooserUIState.value.currentColor.blue,
            onValueChange = { colorChooserViewModel.setBlue(it) }
        )
        Slider(
            value = colorChooserUIState.value.currentColor.green,
            onValueChange = { colorChooserViewModel.setGreen(it) }
        )
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
    modifier: Modifier = Modifier,
    expanded: Boolean,
    setExpanded: (Boolean) -> Unit,
) {


    //var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { setExpanded(it) },
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
                setExpanded(false)
                setColor("")
            }
        ) {
            colorsList.forEach { color ->
                DropdownMenuItem(
                    text = { Text(color) },
                    onClick = {
                        setColor(color)
                        setExpanded(false)
                    }
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ColorChooserPreview() {
    ColorChooserTheme {
        ColorChooserApp(
            Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    }
}