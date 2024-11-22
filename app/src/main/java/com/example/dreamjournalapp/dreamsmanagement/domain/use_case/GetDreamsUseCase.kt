package com.example.dreamjournalapp.dreamsmanagement.domain.use_case

import com.example.dreamjournalapp.dreamsmanagement.data.model.DreamEntity
import com.example.dreamjournalapp.dreamsmanagement.domain.model.DreamDomainModel
import com.example.dreamjournalapp.dreamsmanagement.domain.repository.DreamRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart

class GetDreamsUseCase(
    private val repository: DreamRepository
) {
    operator fun invoke(): Flow<List<DreamDomainModel>>{
        return repository.getDreams()
            .onStart { emit(emptyList()) }
            .catch { emit(emptyList())}
    }

}
