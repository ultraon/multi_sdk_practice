package com.ultraon.calc_sdk.ui.model

import android.os.Parcelable
import androidx.compose.runtime.saveable.Saver
import com.ultraon.calc_sdk.ui.model.SizeCalculatorAppState.SizeCalculatorInitial
import com.ultraon.calc_sdk.ui.model.SizeCalculatorAppState.SizeCalculatorResult
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class SizeCalculatorAppState : Parcelable {
    abstract val heightCm: Int
    abstract val weightKg: Int

    data class SizeCalculatorInitial(
        override val heightCm: Int = 0,
        override val weightKg: Int = 0,
    ) : SizeCalculatorAppState()

    data class SizeCalculatorResult(
        override val heightCm: Int,
        override val weightKg: Int,
        val size: String,
    ) : SizeCalculatorAppState()
}

fun SizeCalculatorInitial.toResult(size: String): SizeCalculatorResult =
    SizeCalculatorResult(heightCm, weightKg, size)

fun SizeCalculatorResult.toInitial(): SizeCalculatorInitial =
    SizeCalculatorInitial(heightCm, weightKg)
