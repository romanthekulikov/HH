package com.roman_kulikov.data.di

import android.content.Context
import androidx.room.Room
import com.roman_kulikov.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule(private val applicationContext: Context) {
    @Singleton
    @Provides
    fun provideDb() : AppDatabase {
        return Room.databaseBuilder(applicationContext, AppDatabase::class.java, "hh_db").build()
    }
}