package com.roman_kulikov.hh

import android.content.Context
import android.os.Build
import android.os.Vibrator
import android.os.VibratorManager
import com.roman_kulikov.domain.use_cases.Declension

val peopleDeclensionMap = mapOf(
    Declension.NOMINATIVE to "человек",
    Declension.GENITIVE to "человека",
    Declension.PLURAL to "человек"
)

val vacancyDeclensionMap = mapOf(
    Declension.NOMINATIVE to "вакансия",
    Declension.GENITIVE to "вакансия",
    Declension.PLURAL to "вакансий"
)

val offerIconIdToResMap = mapOf(
    "near_vacancies" to R.drawable.ic_mark,
    "level_up_resume" to R.drawable.ic_star,
    "temporary_job" to R.drawable.ic_notes
)

fun getVibrator(context: Context): Vibrator =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        (context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager).defaultVibrator
    } else {
        context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }