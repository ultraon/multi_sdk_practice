package com.ultraon.calc_sdk.api

// TODO: write documentation
interface ICalcApi {
    fun calculateSize(height: Double, weight: Double): CalcSize
}

enum class CalcSize {
    S,
    M,
    L,
    XL
}
