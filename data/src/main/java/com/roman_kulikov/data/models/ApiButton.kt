package com.roman_kulikov.data.models

import com.roman_kulikov.domain.entities.Button
import com.squareup.moshi.Json

data class ApiButton(
    @Json(name = "text")
    override val text: String

) : Button
