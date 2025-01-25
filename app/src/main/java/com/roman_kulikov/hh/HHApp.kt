package com.roman_kulikov.hh

import android.app.Application
import com.roman_kulikov.hh.di.AppComponent
import com.roman_kulikov.hh.di.DaggerAppComponent

class HHApp : Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        appComponent = DaggerAppComponent.builder().build()
        super.onCreate()
    }
}