package com.roman_kulikov.data.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.roman_kulikov.domain.entities.Experience

@Entity(tableName = "experience")
data class EntityExperience(
    @ColumnInfo("preview_text")
    override val previewText: String,

    @ColumnInfo("experience_text")
    override val text: String

) : Experience
