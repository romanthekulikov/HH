package com.roman_kulikov.hh

import android.app.Application
import com.roman_kulikov.data.di.DbModule
import com.roman_kulikov.hh.di.AppComponent
import com.roman_kulikov.hh.di.DaggerAppComponent

class HHApp : Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        appComponent = DaggerAppComponent.builder().dbModule(DbModule(this)).build()
        super.onCreate()
    }
}