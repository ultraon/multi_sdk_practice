package com.ultraon.calc_sdk_ui

import android.content.Context
import android.content.Intent
import com.ultraon.calc_sdk.CalcApiSdk

/**
 * Shows a size recommendation screen.
 */
fun CalcApiSdk.showSizeRecommendation(activity: Context) {
    val intent = Intent(activity, CalculationActivity::class.java)
    activity.startActivity(intent)
}
