package com.example.dreamjournalapp.dreamsmanagement.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DreamEntity(
    val title: String,
    val description: String,
    val date: String,
    val imageUri: String?,
    @PrimaryKey val id: Int? = null
)
