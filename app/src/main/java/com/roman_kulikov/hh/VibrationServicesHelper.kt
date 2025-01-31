package com.roman_kulikov.hh

import android.os.VibrationEffect
import android.os.Vibrator

interface VibrationServicesHelper {
    val vibrator: Vibrator?

    fun withVibration(vibrationEffect: VibrationEffect = VibrationEffect.createOneShot(100, 1), job: () -> Unit) {
        vibrator?.vibrate(vibrationEffect)
        job()
    }
}