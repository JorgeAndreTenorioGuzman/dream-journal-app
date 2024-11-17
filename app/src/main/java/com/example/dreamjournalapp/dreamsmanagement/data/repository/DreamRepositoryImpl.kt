package com.example.dreamjournalapp.dreamsmanagement.data.repository

import com.example.dreamjournalapp.dreamsmanagement.data.local.DreamDao
import com.example.dreamjournalapp.dreamsmanagement.data.mapper.DreamMapper.toDomainModel
import com.example.dreamjournalapp.dreamsmanagement.data.mapper.DreamMapper.toDreamEntity
import com.example.dreamjournalapp.dreamsmanagement.data.model.DreamEntity
import com.example.dreamjournalapp.dreamsmanagement.domain.model.DreamDomainModel
import com.example.dreamjournalapp.dreamsmanagement.domain.repository.DreamRepository
import kotlinx.coroutines.flow.Flow

class DreamRepositoryImpl(
    private val dao: DreamDao
): DreamRepository {
    override fun getDreams(): Flow<List<DreamDomainModel>> {
        return dao.getDreams().toDomainModel()
    }

    override suspend fun getDreamById(id: Int): DreamDomainModel? {
        return dao.getDreamById(id)?.toDomainModel()
    }

    override suspend fun insertDream(dream: DreamDomainModel) {
        return dao.insertDream(dream.toDreamEntity())
    }

    override suspend fun deleteDream(dream: DreamEntity) {
        return dao.deleteDream(dream)
    }

    override suspend fun addDreamToFavourites(dreamId: Int, isFavorite: Boolean) {
        return dao.updateFavoriteStatus(dreamId, isFavorite)
    }

    override fun getFavoritesDreams(): Flow<List<DreamDomainModel>> {
        return dao.getFavoriteDreams().toDomainModel()
    }
}