package com.example.dreamjournalapp.dreamsmanagement.domain.use_case

import com.example.dreamjournalapp.dreamsmanagement.data.model.DreamEntity
import com.example.dreamjournalapp.dreamsmanagement.domain.repository.DreamRepository

class AddDreamUseCase(
    private val repository: DreamRepository
) {
    suspend operator fun invoke(dream: DreamEntity){
        repository.insertDream(dream)
    }

}
