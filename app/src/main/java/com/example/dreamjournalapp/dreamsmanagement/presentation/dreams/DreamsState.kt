package com.example.dreamjournalapp.dreamsmanagement.presentation.dreams

import com.example.dreamjournalapp.dreamsmanagement.domain.model.DreamDomainModel

data class DreamsState (
    val dreams: List<DreamDomainModel> = emptyList()
)