package com.example.dreamjournalapp.dreamsmanagement.domain.use_case

import com.example.dreamjournalapp.dreamsmanagement.domain.model.DreamDomainModel
import com.example.dreamjournalapp.dreamsmanagement.domain.repository.DreamRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteDreams(
    private val repository: DreamRepository
) {
     operator fun invoke(): Flow<List<DreamDomainModel>>{
        return repository.getFavoritesDreams()
    }
}
