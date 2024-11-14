package com.example.dreamjournalapp.dreamsmanagement.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.ZonedDateTime

@Entity
data class DreamEntity(
    val title: String,
    val description: String,
    val creationTime: ZonedDateTime,
    val imageUri: String?,
    @PrimaryKey val id: Int? = null
)
