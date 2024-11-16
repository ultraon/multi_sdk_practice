package com.ultraon.calc_sdk.impl

import com.ultraon.calc_sdk.api.CalcSize
import org.junit.Assert.*
import org.junit.Test

class DefaultCalcApiTest {

    @Test
    fun testCalculateSizeHeightLessThanZero() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            DefaultCalcApi().calculateSize(-1.0, 70.0)
        }
        assertEquals("Height must be greater than zero", exception.message)
    }

    @Test
    fun testCalculateSizeWeightLessThanZero() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            DefaultCalcApi().calculateSize(175.0, -70.0)
        }
        assertEquals("Weight must be greater than zero", exception.message)
    }

    @Test
    fun testCalculateSizeHeightZero() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            DefaultCalcApi().calculateSize(0.0, 70.0)
        }
        assertEquals("Height must be greater than zero", exception.message)
    }

    @Test
    fun testCalculateSizeWeightZero() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            DefaultCalcApi().calculateSize(175.0, 0.0)
        }
        assertEquals("Weight must be greater than zero", exception.message)
    }

    @Test
    fun testCalculateSizeValid() {
        val api = DefaultCalcApi()
        assertEquals(CalcSize.S, api.calculateSize(175.0, 50.0))
        assertEquals(CalcSize.M, api.calculateSize(175.0, 70.0))
        assertEquals(CalcSize.L, api.calculateSize(175.0, 85.0))
        assertEquals(CalcSize.XL, api.calculateSize(175.0, 100.0))
    }
}
