package com.roman_kulikov.hh

import com.roman_kulikov.domain.use_cases.Declension

val peopleResDeclensionMap = mapOf(
    Declension.NOMINATIVE to "человек",
    Declension.GENITIVE to "человека",
    Declension.PLURAL to "человек"
)

val offerIconIdToResMap = mapOf(
    "near_vacancies" to R.drawable.ic_mark,
    "level_up_resume" to R.drawable.ic_star,
    "temporary_job" to R.drawable.ic_notes
)