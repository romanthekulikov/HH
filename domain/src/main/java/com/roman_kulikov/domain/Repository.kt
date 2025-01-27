package com.roman_kulikov.domain

import com.roman_kulikov.domain.entities.Response
import com.roman_kulikov.tools.Result

interface Repository {
    suspend fun getData(): Result<Response>
}