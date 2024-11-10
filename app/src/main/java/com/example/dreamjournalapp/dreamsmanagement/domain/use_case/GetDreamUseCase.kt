package com.example.dreamjournalapp.dreamsmanagement.domain.use_case

import com.example.dreamjournalapp.dreamsmanagement.data.model.DreamEntity
import com.example.dreamjournalapp.dreamsmanagement.domain.repository.DreamRepository
import kotlinx.coroutines.flow.Flow

class GetDreamUseCase(
    private val repository: DreamRepository
) {

    suspend operator fun invoke(id: Int): DreamEntity?{
        return repository.getDreamById(id)
    }
}
