package com.ultraon.calc_sdk.api

/**
 * Interface for calculating size based on height and weight.
 * The result is one of the [CalcSize] values.
 * @see CalcSize
 */
interface ICalcApi {
    fun calculateSize(heightCm: Double, weightKg: Double): CalcSize
}

/**
 * Enum class for size calculation result.
 * @see ICalcApi.calculateSize
 */
enum class CalcSize {
    S,
    M,
    L,
    XL
}
