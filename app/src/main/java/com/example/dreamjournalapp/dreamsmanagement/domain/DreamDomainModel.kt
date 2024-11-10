package com.example.dreamjournalapp.dreamsmanagement.domain

data class DreamDomainModel (
    val id: Int?,
    val title: String,
    val description: String,
    val date: String,
    val imageUri: String?
)