package com.roman_kulikov.data.models

import com.roman_kulikov.domain.entities.Experience
import com.squareup.moshi.Json

data class ApiExperience(
    @Json(name = "previewText")
    override val previewText: String,

    @Json(name = "text")
    override val text: String

) : Experience
