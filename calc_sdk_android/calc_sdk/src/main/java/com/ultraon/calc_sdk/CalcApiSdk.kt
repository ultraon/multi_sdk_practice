package com.ultraon.calc_sdk

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import com.ultraon.calc_sdk.api.ICalcApi
import com.ultraon.calc_sdk.impl.DefaultCalcApi
import com.ultraon.calc_sdk.ui.CalculationActivity
import java.util.concurrent.atomic.AtomicBoolean

// TODO: write documentation
object CalcApiSdk {
    private val _isInitialized: AtomicBoolean = AtomicBoolean(false)
    val isInitialized: Boolean
        get() = _isInitialized.get()

    suspend fun init(app: Application) {
        if (_isInitialized.getAndSet(true)) return
        // TODO: Implement SDK initialization
    }

    fun createApi(): ICalcApi = DefaultCalcApi()

    fun showSizeRecommendation(activity: Context) {
        val intent = Intent(activity, CalculationActivity::class.java)
        activity.startActivity(intent)
    }
}
