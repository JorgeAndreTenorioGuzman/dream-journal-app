package com.example.dreamjournalapp.dreamsmanagement.domain.model

import java.time.ZonedDateTime

data class DreamDomainModel (
    val id: Int?,
    val title: String,
    val description: String,
    val creationTime: ZonedDateTime,
    val imageUri: String?
)