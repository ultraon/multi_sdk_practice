package com.ultraon.calc_sdk_ui

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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.ultraon.calc_sdk.CalcApiSdk
import com.ultraon.calc_sdk_ui.model.SizeCalculatorAppState
import com.ultraon.calc_sdk_ui.model.SizeCalculatorAppState.SizeCalculatorInitial
import com.ultraon.calc_sdk_ui.model.SizeCalculatorAppState.SizeCalculatorResult
import com.ultraon.calc_sdk_ui.model.toInitial
import com.ultraon.calc_sdk_ui.model.toResult
import com.ultraon.calc_sdk_ui.theme.SampleTheme
import com.ultraon.calc_sdk_ui.view.RecommendedSizeScreen
import com.ultraon.calc_sdk_ui.view.SizeCalculatorScreen

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
                var appState by rememberSaveable {
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
                                onCalculateClick(heightCm, weightKg)?.let {
                                    appState = it
                                }
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

    private fun onCalculateClick(heightCm: Int, weightKg: Int): SizeCalculatorAppState? {
        if (heightCm <= 0 || weightKg <= 0) {
            // show toast
            Toast.makeText(
                this@CalculationActivity,
                "Height and weight must be greater than zero",
                Toast.LENGTH_SHORT
            ).show()
            return null
        }
        val size = calcApi.calculateSize(
            heightCm.toDouble(),
            weightKg.toDouble(),
        ).name
        return SizeCalculatorInitial(heightCm, weightKg).toResult(size)
    }
}
