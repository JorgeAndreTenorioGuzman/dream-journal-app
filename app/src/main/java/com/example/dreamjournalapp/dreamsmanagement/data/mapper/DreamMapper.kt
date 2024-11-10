package com.example.dreamjournalapp.dreamsmanagement.data.mapper

import com.example.dreamjournalapp.dreamsmanagement.data.model.DreamEntity
import com.example.dreamjournalapp.dreamsmanagement.domain.DreamDomainModel

class DreamMapper {

    fun mapToDomain(dreamEntity: DreamEntity): DreamDomainModel {
        return DreamDomainModel(
            id = dreamEntity.id,
            title = dreamEntity.title,
            description = dreamEntity.description,
            date = dreamEntity.date,
            imageUri = dreamEntity.imageUri
        )
    }

    fun mapToEntity(dreamDomainModel: DreamDomainModel): DreamEntity {
        return DreamEntity(
            id = dreamDomainModel.id,
            title = dreamDomainModel.title,
            description = dreamDomainModel.description,
            date = dreamDomainModel.date,
            imageUri = dreamDomainModel.imageUri
        )
    }

    fun mapToDomainList(dreamEntities: List<DreamEntity>): List<DreamDomainModel> {
        return dreamEntities.map { mapToDomain(it) }
    }

    fun mapToEntityList(dreamDomainModel: List<DreamDomainModel>): List<DreamEntity> {
        return dreamDomainModel.map { mapToEntity(it) }
    }
}