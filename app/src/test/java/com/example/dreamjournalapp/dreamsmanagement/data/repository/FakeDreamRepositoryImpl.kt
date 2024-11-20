package com.example.dreamjournalapp.dreamsmanagement.data.repository

import com.example.dreamjournalapp.dreamsmanagement.data.model.DreamEntity
import com.example.dreamjournalapp.dreamsmanagement.domain.model.DreamDomainModel
import com.example.dreamjournalapp.dreamsmanagement.domain.repository.DreamRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeDreamRepositoryImpl: DreamRepository {

    private val dreams = mutableListOf<DreamDomainModel>()

    override fun getDreams(): Flow<List<DreamDomainModel>> {
        return flow { emit(dreams) }
    }

    override suspend fun getDreamById(id: Int): DreamDomainModel? {
        return dreams.find { it.id == id }
    }

    override suspend fun insertDream(dream: DreamDomainModel) {
        dreams.add(dream)
    }

    override suspend fun deleteDream(dream: DreamEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun addDreamToFavourites(dreamId: Int, isFavorite: Boolean) {
        val dream = dreams.find { it.id == dreamId }
        dream?.let {
            it.isFavorite = isFavorite
        }
    }

    override fun getFavoritesDreams(): Flow<List<DreamDomainModel>> {
        TODO("Not yet implemented")
    }
}