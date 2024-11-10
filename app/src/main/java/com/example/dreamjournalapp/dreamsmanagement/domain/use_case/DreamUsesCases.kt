package com.example.dreamjournalapp.dreamsmanagement.domain.use_case

data class DreamUsesCases(
    val getDreams: GetDreamsUseCase,
    val getDream: GetDreamUseCase,
    val addDream: AddDreamUseCase
)
