package com.example.dreamjournalapp.dreamsmanagement.domain.repository

import com.example.dreamjournalapp.dreamsmanagement.data.model.DreamEntity
import com.example.dreamjournalapp.dreamsmanagement.domain.model.DreamDomainModel
import kotlinx.coroutines.flow.Flow

interface DreamRepository {

    fun getDreams(): Flow<List<DreamDomainModel>>

    suspend fun getDreamById(id: Int): DreamDomainModel?

    suspend fun insertDream(dream: DreamEntity)

    suspend fun deleteDream(dream: DreamEntity)
}