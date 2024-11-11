package com.example.dreamjournalapp.dreamsmanagement.data.mapper

import com.example.dreamjournalapp.dreamsmanagement.data.model.DreamEntity
import com.example.dreamjournalapp.dreamsmanagement.domain.model.DreamDomainModel

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

}