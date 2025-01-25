package com.roman_kulikov.data.di

import com.roman_kulikov.data.converters.LocalDateConverter
import com.roman_kulikov.data.api.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://drive.usercontent.google.com/"

@Module
class ApiModule {
    @Singleton
    @Provides
    fun getApiService(): ApiService {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(LocalDateConverter())
            .build()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL).build()
        return retrofit.create(ApiService::class.java)
    }
}

