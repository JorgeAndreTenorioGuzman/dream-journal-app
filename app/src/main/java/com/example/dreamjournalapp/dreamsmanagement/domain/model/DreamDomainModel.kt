package com.example.dreamjournalapp.dreamsmanagement.domain.model

import java.time.ZonedDateTime

data class DreamDomainModel (
    val id: Int?,
    val title: String,
    val description: String,
    val creationTime: ZonedDateTime,
    var isFavorite: Boolean = false,
    val imageUri: String?
)

class InvalidDreamException(message: String): Exception(message)