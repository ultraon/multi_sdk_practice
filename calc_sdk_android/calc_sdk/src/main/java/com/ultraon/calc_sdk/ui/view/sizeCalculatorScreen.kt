package com.ultraon.calc_sdk.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.ultraon.calc_sdk.R
import com.ultraon.calc_sdk.ui.theme.SampleTheme

@Composable
fun SizeCalculatorScreen(
    modifier: Modifier = Modifier,
    initialHeightCm: Int = 0,
    initialWeightKg: Int = 0,
    onCalculateClick: (heightCm: Int, weightKg: Int) -> Unit = { _, _ -> },
) {
    var heightCm by remember { mutableIntStateOf(initialHeightCm) }
    var weightKg by remember { mutableIntStateOf(initialWeightKg) }
    val isValidInput by remember { derivedStateOf { heightCm > 0 && weightKg > 0 } }

    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.calc_sdk_find_perfect_fit),
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(16.dp))
            NumberInput(
                value = heightCm,
                label = stringResource(id = R.string.calc_sdk_height_cm),
                imeAction = ImeAction.Next,
                onValueChange = { heightCm = it },
            )
            Spacer(modifier = Modifier.height(8.dp))
            NumberInput(
                value = weightKg,
                label = stringResource(id = R.string.calc_sdk_weight_kg),
                imeAction = ImeAction.Done,
                keyboardActions = KeyboardActions(
                    onDone = {
                        if (isValidInput) {
                            onCalculateClick(heightCm, weightKg)
                        }
                    }
                ),
                onValueChange = { weightKg = it },
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.defaultMinSize(minWidth = 120.dp),
                enabled = isValidInput,
                onClick = {
                    if (isValidInput) {
                        onCalculateClick(heightCm, weightKg)
                    }
                }) {
                Text(stringResource(R.string.calc_sdk_get_size_recommendation))
            }
        }
    }
}

@PreviewLightDark
@Composable
fun SizeCalculatorScreenPreview() {
    SampleTheme {
        SizeCalculatorScreen()
    }
}
