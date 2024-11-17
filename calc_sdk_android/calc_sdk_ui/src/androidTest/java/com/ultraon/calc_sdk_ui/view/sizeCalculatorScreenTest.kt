package com.ultraon.calc_sdk_ui.view

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ultraon.calc_sdk_ui.R
import com.ultraon.calc_sdk_ui.theme.SampleTheme
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SizeCalculatorScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun sizeCalculatorScreen_displaysCorrectInitialValues() = runTest {
        val initialHeight = 170
        val initialWeight = 70
        composeTestRule.setContent {
            SampleTheme {
                SizeCalculatorScreen(
                    initialHeightCm = initialHeight,
                    initialWeightKg = initialWeight,
                )
            }
        }
        composeTestRule.awaitIdle()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.calc_sdk_height_cm),
        )
            .assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.calc_sdk_weight_kg),
        )
            .assertExists()
    }

    @Test
    fun sizeCalculatorScreen_initialValue_calculateButtonClick() = runTest {
        val initialHeight = 170
        val initialWeight = 70
        var calculatedHeight = 0
        var calculatedWeight = 0
        composeTestRule.setContent {
            SampleTheme {
                SizeCalculatorScreen(
                    initialHeightCm = initialHeight,
                    initialWeightKg = initialWeight,
                    onCalculateClick = { height, weight ->
                        calculatedHeight = height
                        calculatedWeight = weight
                    }
                )
            }
        }
        composeTestRule.awaitIdle()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.calc_sdk_get_size_recommendation),
        )
            .performClick()

        assert(calculatedHeight == initialHeight)
        assert(calculatedWeight == initialWeight)
    }

    @Test
    fun sizeCalculatorScreen_inputFieldsWork() = runTest {
        composeTestRule.setContent {
            SizeCalculatorScreen()
        }

        // Enter height value
        val heightInput = "175"
        composeTestRule.onNodeWithTag("heightInput")
            .performTextInput(heightInput)

        // Enter weight value
        val weightInput = "70"
        composeTestRule.onNodeWithTag("weightInput")
            .performTextInput(weightInput)

        // Assert that the state variables have been updated
        composeTestRule.onNodeWithText(heightInput).assertExists()
        composeTestRule.onNodeWithText(weightInput).assertExists()
    }

    @Test
    fun sizeCalculatorScreen_inputValue_calculateButtonClick() = runTest {
        val inputHeight = 170
        val inputWeight = 70
        var calculatedHeight = 0
        var calculatedWeight = 0
        composeTestRule.setContent {
            SampleTheme {
                SizeCalculatorScreen(
                    onCalculateClick = { height, weight ->
                        calculatedHeight = height
                        calculatedWeight = weight
                    }
                )
            }
        }
        composeTestRule.awaitIdle()
        // Enter height value
        val heightInput = "$inputHeight"
        composeTestRule.onNodeWithTag("heightInput")
            .performTextInput(heightInput)

        // Enter weight value
        val weightInput = "$inputWeight"
        composeTestRule.onNodeWithTag("weightInput")
            .performTextInput(weightInput)
        composeTestRule.awaitIdle()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.calc_sdk_get_size_recommendation),
        )
            .performClick()

        assert(calculatedHeight == inputHeight)
        assert(calculatedWeight == inputWeight)
    }
}
