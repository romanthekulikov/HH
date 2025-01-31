package com.roman_kulikov.domain.use_cases

import javax.inject.Inject
import kotlin.math.abs

class DeclensionUseCase @Inject constructor() {
    operator fun invoke(number: Int): Declension {
        val lastNumber = abs(number) % 10
        return when (lastNumber) {
            1 -> Declension.NOMINATIVE
            in 2..4 -> Declension.GENITIVE
            else -> Declension.PLURAL
        }
    }

}