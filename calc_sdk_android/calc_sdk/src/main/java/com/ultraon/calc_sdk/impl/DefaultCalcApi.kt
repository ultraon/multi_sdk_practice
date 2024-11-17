package com.ultraon.calc_sdk.impl

import com.ultraon.calc_sdk.api.CalcSize
import com.ultraon.calc_sdk.api.ICalcApi

/**
 * Default implementation of the [ICalcApi] interface.
 * This implementation calculates the size recommendation based on the BMI.
 */
class DefaultCalcApi : ICalcApi {
    /**
     * Calculates the size recommendation based on the BMI.
     *
     * The logic of the size recommendation is the following:
     * - S: BMI < 18.5
     * - M: 18.5 <= BMI < 24.9
     * - L: 25 <= BMI < 29.9
     * - XL: BMI >= 30
     *
     * @param heightCm Height in centimeters.
     * @param weightKg Weight in kilograms.
     * @return [CalcSize] value.
     * @throws IllegalArgumentException if the height or weight is less than or equal to zero.
     * @see CalcSize
     * @see ICalcApi
     *
     */
    override fun calculateSize(heightCm: Double, weightKg: Double): CalcSize {
        require(heightCm > 0) { "Height must be greater than zero" }
        require(weightKg > 0) { "Weight must be greater than zero" }

        val heightM = heightCm / 100
        val bmi = weightKg / (heightM * heightM)
        return when {
            bmi < 18.5 -> CalcSize.S
            bmi < 24.9 -> CalcSize.M
            bmi < 29.9 -> CalcSize.L
            else -> CalcSize.XL
        }
    }
}
