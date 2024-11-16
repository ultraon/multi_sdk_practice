package com.ultraon.calc_sdk.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.ultraon.calc_sdk.CalcApiSdk
import com.ultraon.calc_sdk.ui.SizeCalculatorAppState.SizeCalculatorInitial
import com.ultraon.calc_sdk.ui.SizeCalculatorAppState.SizeCalculatorResult
import com.ultraon.calc_sdk.ui.theme.SampleTheme

class CalculationActivity : ComponentActivity() {
    private val calcApi by lazy { CalcApiSdk.createApi() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LaunchedEffect(key1 = Unit) {
                CalcApiSdk.init(application)
            }

            SampleTheme {
                var appState by remember {
                    mutableStateOf<SizeCalculatorAppState>(
                        SizeCalculatorInitial()
                    )
                }
                AnimatedContent(
                    modifier = Modifier.background(MaterialTheme.colorScheme.background),
                    targetState = appState,
                    label = "screen_switch",
                ) { state ->
                    when (state) {
                        is SizeCalculatorInitial -> {
                            SizeCalculatorScreen(
                                initialHeightCm = state.heightCm,
                                initialWeightKg = state.weightKg,
                            ) { heightCm, weightKg ->
                                if (heightCm <= 0.0 || weightKg <= 0.0) {
                                    // show toast
                                    Toast.makeText(
                                        this@CalculationActivity,
                                        "Height and weight must be greater than zero",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    return@SizeCalculatorScreen
                                }
                                val size = calcApi.calculateSize(heightCm, weightKg).name
                                appState = state.copy(
                                    heightCm = heightCm,
                                    weightKg = weightKg
                                ).toResult(size)
                            }
                        }

                        is SizeCalculatorResult -> {
                            RecommendedSizeScreen(size = state.size) {
                                appState = state.toInitial()
                            }
                        }
                    }
                }
            }
        }
    }
}
