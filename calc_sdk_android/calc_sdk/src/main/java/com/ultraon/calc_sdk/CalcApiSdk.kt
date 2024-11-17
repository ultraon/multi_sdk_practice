package com.ultraon.calc_sdk

import android.app.Application
import com.ultraon.calc_sdk.api.ICalcApi
import com.ultraon.calc_sdk.impl.DefaultCalcApi
import java.util.concurrent.atomic.AtomicBoolean

/**
 * SDK object for initializing and creating [ICalcApi] instance.
 * @see ICalcApi
 */
object CalcApiSdk {
    private val _isInitialized: AtomicBoolean = AtomicBoolean(false)
    /**
     * Returns `true` if the SDK is initialized.
     */
    val isInitialized: Boolean
        get() = _isInitialized.get()

    /**
     * Initializes the SDK.
     * @param app [Application] instance.
     * @see isInitialized
     */
    suspend fun init(app: Application) {
        if (_isInitialized.getAndSet(true)) return
    }

    /**
     * Creates an instance of [ICalcApi].
     * @return [ICalcApi] instance.
     */
    fun createApi(): ICalcApi = DefaultCalcApi()
}
