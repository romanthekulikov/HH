package com.roman_kulikov.data.api.models

import com.roman_kulikov.domain.entities.Experience
import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
data class ApiExperience(
    @Json(name = "previewText")
    override val previewText: String,

    @Json(name = "text")
    override val text: String

) : Experience
