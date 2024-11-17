package com.example.dreamjournalapp.dreamsmanagement.domain.use_case

import com.example.dreamjournalapp.dreamsmanagement.domain.repository.DreamRepository

class AddDreamToFavourites(
    private val repository: DreamRepository
) {
    suspend operator fun invoke(dreamId: Int, isFavorite: Boolean){
        repository.addDreamToFavourites(dreamId, isFavorite)
    }
}
