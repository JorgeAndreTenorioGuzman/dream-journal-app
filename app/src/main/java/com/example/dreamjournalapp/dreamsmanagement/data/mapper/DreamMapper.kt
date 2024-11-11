package com.example.dreamjournalapp.dreamsmanagement.data.mapper

import com.example.dreamjournalapp.dreamsmanagement.data.model.DreamEntity
import com.example.dreamjournalapp.dreamsmanagement.domain.model.DreamDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object DreamMapper {

    fun DreamEntity.toDomainModel(): DreamDomainModel {
        return DreamDomainModel(
            id = this.id,
            title = this.title,
            date = this.date,
            imageUri = this.imageUri,
            description = this.description,
        )
    }

    fun DreamDomainModel.toDreamEntity(): DreamEntity {
        return DreamEntity(
            id = this.id,
            title = this.title,
            description = this.description,
            imageUri = this.imageUri,
            date = this.date
        )
    }

    fun Flow<List<DreamEntity>>.toDomainModel(): Flow<List<DreamDomainModel>> {
        return this.map { entities ->
            entities.map { it.toDomainModel() }
        }
    }

}