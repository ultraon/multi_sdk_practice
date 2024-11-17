package com.ultraon.calc_sdk_ui.view

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ultraon.calc_sdk_ui.R
import com.ultraon.calc_sdk_ui.theme.SampleTheme
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RecommendedSizeScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun recommendedSizeScreen_displaysCorrectSize() = runTest {
        val size = "M"
        composeTestRule.setContent {
            SampleTheme {
                RecommendedSizeScreen(size = size)
            }
        }
        composeTestRule.awaitIdle()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.calc_sdk_your_recommended_size, size))
            .assertExists()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.calc_sdk_size_recommended, size))
            .assertExists()
    }

    @Test
    fun recommendedSizeScreen_okButtonClick() = runTest {
        var clicked = false
        composeTestRule.setContent {
            SampleTheme {
                RecommendedSizeScreen(size = "M", onOkClick = { clicked = true })
            }
        }
        composeTestRule.awaitIdle()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.calc_sdk_ok))
            .performClick()

        assert(clicked)
    }
}
