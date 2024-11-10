package com.example.dreamjournalapp.dreamsmanagement.domain.use_case

import com.example.dreamjournalapp.dreamsmanagement.data.model.DreamEntity
import com.example.dreamjournalapp.dreamsmanagement.domain.repository.DreamRepository
import kotlinx.coroutines.flow.Flow

class GetDreamsUseCase(
    private val repository: DreamRepository
) {
    operator fun invoke(): Flow<List<DreamEntity>>{
        return repository.getDreams()
    }

}
