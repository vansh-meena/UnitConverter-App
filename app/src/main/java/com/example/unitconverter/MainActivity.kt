package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                UnitConverter()
            }
        }
    }
}

@Composable
fun UnitConverter(){

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meter") }
    var outputUnit by remember { mutableStateOf("Meter") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableDoubleStateOf(1.0) }
    val oConversionFactor = remember { mutableDoubleStateOf(1.0) }

    val customTextStyle = TextStyle(
        fontFamily = FontFamily.Default, //font family
        fontSize = 32.sp,          //font size
        color = Color.Red        //text color

    )

    fun convertUnit(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0   // ?: --> elvis operator (if the value is not double or null it will assign 0.0 as the value)
        val result = (inputValueDouble * conversionFactor.doubleValue * 100.0 / oConversionFactor.doubleValue).roundToInt() / 100.0
        outputValue = result.toString()
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        //Here UI Elements will be stacked below to each other
        Text("Unit Converter", style = customTextStyle)
        Spacer(modifier = Modifier.height(16.dp))

//        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField (
            value = inputValue,
            onValueChange = {
                //Here goes what should happen, when the value of our OutlinedTextField changes
                inputValue = it
                convertUnit()
            },
            label = { Text("Enter Value") })
        Row {
            //Here UI elements will be stacked next to each other
            //Input Box
            Box{
                //Input Button
                Button(onClick = {iExpanded = true}) {
                    Text(inputUnit)
                    Icon(
                        Icons.Default.ArrowDropDown ,
                        contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iExpanded , onDismissRequest = {iExpanded = false}) {
                    DropdownMenuItem(
                        text = { Text("Centimeter")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Centimeter"
                            conversionFactor.doubleValue = 0.01
                            convertUnit()
//                            label = {Text (inputUnit)}
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meter")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Meter"
                            conversionFactor.doubleValue = 1.0
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Feet"
                            conversionFactor.doubleValue = 0.3048
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Millimeter")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Millimeter"
                            conversionFactor.doubleValue = 0.001
                            convertUnit()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            //Output Box
            Box{
                //Output Button
                Button(onClick = {oExpanded = true}) {
                    Text(outputUnit)
                    Icon(
                        Icons.Default.ArrowDropDown ,
                        contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpanded , onDismissRequest = {oExpanded = false}) {
                    DropdownMenuItem(
                        text = { Text("Centimeter")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Centimeter"
                            oConversionFactor.doubleValue = 0.01
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meter")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Meter"
                            oConversionFactor.doubleValue = 1.0
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Feet"
                            oConversionFactor.doubleValue = 0.3048
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Millimeter")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Millimeter"
                            oConversionFactor.doubleValue = 0.001
                            convertUnit()
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        //Result text
        Text("Result : $outputValue $outputUnit",
                style = MaterialTheme.typography.headlineSmall
            )

    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}