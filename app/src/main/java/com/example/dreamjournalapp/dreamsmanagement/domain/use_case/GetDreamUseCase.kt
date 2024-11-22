package com.example.dreamjournalapp.dreamsmanagement.domain.use_case

import com.example.dreamjournalapp.dreamsmanagement.data.model.DreamEntity
import com.example.dreamjournalapp.dreamsmanagement.domain.model.DreamDomainModel
import com.example.dreamjournalapp.dreamsmanagement.domain.model.DreamNotFoundException
import com.example.dreamjournalapp.dreamsmanagement.domain.model.InvalidDreamException
import com.example.dreamjournalapp.dreamsmanagement.domain.repository.DreamRepository
import kotlinx.coroutines.flow.Flow
import kotlin.jvm.Throws

class GetDreamUseCase(
    private val repository: DreamRepository
) {

    @Throws(DreamNotFoundException::class)
    suspend operator fun invoke(id: Int): DreamDomainModel?{
        val dream = repository.getDreamById(id)
        if(dream == null) {
            throw DreamNotFoundException("Dream with ID $id not found")
        }
        return dream
    }
}
