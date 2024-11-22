package com.example.dreamjournalapp.dreamsmanagement.domain.use_case

import com.example.dreamjournalapp.dreamsmanagement.domain.model.InvalidDreamException
import com.example.dreamjournalapp.dreamsmanagement.domain.repository.DreamRepository

class AddDreamToFavourites(
    private val repository: DreamRepository
) {
    @Throws(InvalidDreamException::class)
    suspend operator fun invoke(dreamId: Int, isFavorite: Boolean){
        if(dreamId == null){
            throw InvalidDreamException("Dream Id cannot be null")
        }
        if(dreamId <= 0){
            throw InvalidDreamException("Dream id must be positive number")
        }

        val currentDream = repository.getDreamById(dreamId)
        if(currentDream != null && currentDream.isFavorite == isFavorite){
            return
        }

        repository.addDreamToFavourites(dreamId, isFavorite)
    }
}
