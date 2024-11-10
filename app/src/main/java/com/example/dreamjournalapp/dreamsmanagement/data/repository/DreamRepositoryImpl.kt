package com.example.dreamjournalapp.dreamsmanagement.data.repository

import com.example.dreamjournalapp.dreamsmanagement.data.local.DreamDao
import com.example.dreamjournalapp.dreamsmanagement.data.model.DreamEntity
import com.example.dreamjournalapp.dreamsmanagement.domain.repository.DreamRepository
import kotlinx.coroutines.flow.Flow

class DreamRepositoryImpl(
    private val dao: DreamDao
): DreamRepository {
    override fun getDreams(): Flow<List<DreamEntity>> {
        return dao.getDreams()
    }

    override suspend fun getDreamById(id: Int): DreamEntity? {
        return dao.getDreamById(id)
    }

    override suspend fun insertDream(dream: DreamEntity) {
        return dao.insertDream(dream)
    }

    override suspend fun deleteDream(dream: DreamEntity) {
        return dao.deleteDream(dream)
    }
}