package com.example.dreamjournalapp.dreamsmanagement.domain.use_case

import com.example.dreamjournalapp.dreamsmanagement.data.model.DreamEntity
import com.example.dreamjournalapp.dreamsmanagement.domain.model.DreamDomainModel
import com.example.dreamjournalapp.dreamsmanagement.domain.model.InvalidDreamException
import com.example.dreamjournalapp.dreamsmanagement.domain.repository.DreamRepository

class AddDreamUseCase(
    private val repository: DreamRepository
) {

    @Throws(InvalidDreamException::class)
    suspend operator fun invoke(dream: DreamDomainModel){
        if(dream.title.isBlank()){
            throw InvalidDreamException("The title of the dream can't be empty")
        }
        if(dream.description.isBlank()){
            throw InvalidDreamException("The description of the dream can't be empty")
        }

        repository.insertDream(dream)
    }

}
