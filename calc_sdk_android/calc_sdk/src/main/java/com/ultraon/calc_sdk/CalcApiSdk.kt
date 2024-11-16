package com.ultraon.calc_sdk

import android.app.Activity
import android.app.Application
import com.ultraon.calc_sdk.api.ICalcApi
import com.ultraon.calc_sdk.impl.DefaultCalcApi
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

    fun showSizeRecommendation(context: Activity) {
        // TODO: Show size recommendation page.
    }
}
