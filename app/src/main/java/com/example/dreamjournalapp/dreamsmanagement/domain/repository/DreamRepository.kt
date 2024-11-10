package com.example.dreamjournalapp.dreamsmanagement.domain.repository

import com.example.dreamjournalapp.dreamsmanagement.data.model.DreamEntity
import kotlinx.coroutines.flow.Flow

interface DreamRepository {

    fun getDreams(): Flow<List<DreamEntity>>

    suspend fun getDreamById(id: Int): DreamEntity?

    suspend fun insertDream(dream: DreamEntity)

    suspend fun deleteDream(dream: DreamEntity)
}