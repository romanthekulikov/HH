package com.roman_kulikov.data.api

import com.roman_kulikov.data.api.models.ApiResponse
import retrofit2.http.GET

interface ApiService {
    @GET("/u/0/uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r&export=download")
    suspend fun getData(): ApiResponse

}