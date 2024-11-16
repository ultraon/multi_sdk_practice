package com.ultraon.calc_sdk.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ultraon.calc_sdk.ui.SizeCalculatorAppState.*


sealed interface SizeCalculatorAppState {
    val heightCm: Double
    val weightKg: Double

    data class SizeCalculatorInitial(
        override val heightCm: Double = 0.0,
        override val weightKg: Double = 0.0,
    ) : SizeCalculatorAppState

    data class SizeCalculatorResult(
        override val heightCm: Double,
        override val weightKg: Double,
        val size: String,
    ) : SizeCalculatorAppState
}

fun SizeCalculatorInitial.toResult(size: String): SizeCalculatorResult =
    SizeCalculatorResult(heightCm, weightKg, size)

fun SizeCalculatorResult.toInitial(): SizeCalculatorInitial =
    SizeCalculatorInitial(heightCm, weightKg)

@Composable
fun SizeCalculatorScreen(
    modifier: Modifier = Modifier,
    initialHeightCm: Double = 0.0,
    initialWeightKg: Double = 0.0,
    onCalculateClick: (heightCm: Double, weightKg: Double) -> Unit = { _, _ -> },
) {
    var height by remember { mutableStateOf(if (initialHeightCm > 0.0) "$initialHeightCm" else "") }
    var weight by remember { mutableStateOf(if (initialWeightKg > 0.0) "$initialWeightKg" else "") }

    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = height,
                label = { Text("Height (cm):") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next,
                ),
                onValueChange = { height = it },
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = weight,
                label = { Text("Weight (kg):") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done,
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        if (height.isNotEmpty() && weight.isNotEmpty()) {
                            onCalculateClick(height.toDouble(), weight.toDouble())
                        }
                    }
                ),
                onValueChange = { weight = it },
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.defaultMinSize(minWidth = 120.dp),
                enabled = height.isNotEmpty() && weight.isNotEmpty(),
                onClick = {
                    if (height.isNotEmpty() && weight.isNotEmpty()) {
                        onCalculateClick(height.toDouble(), weight.toDouble())
                    }
                }) {
                Text("Get Size Recommendation")
            }
        }
    }
}

@Composable
fun RecommendedSizeScreen(
    modifier: Modifier = Modifier,
    size: String,
    onOkClick: () -> Unit = {},
) {
    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Your Recommended Size: $size",
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Based on your info, size $size is recommended.")
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.defaultMinSize(minWidth = 120.dp),
                onClick = onOkClick,
            ) {
                Text("OK")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SizeCalculatorScreenPreview() {
    SizeCalculatorScreen()
}

@Preview(showBackground = true)
@Composable
fun RecommendedSizeScreenPreview() {
    RecommendedSizeScreen(size = "S")
}
