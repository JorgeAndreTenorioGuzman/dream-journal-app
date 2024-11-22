package com.example.dreamjournalapp.dreamsmanagement.domain.use_case

import com.example.dreamjournalapp.dreamsmanagement.data.repository.FakeDreamRepositoryImpl
import com.example.dreamjournalapp.dreamsmanagement.domain.model.DreamDomainModel
import io.mockk.coEvery
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.time.ZonedDateTime

class GetDreamsUseCaseTest{

    private lateinit var getDreamsUseCase: GetDreamsUseCase
    private lateinit var fakeDreamRepositoryImpl: FakeDreamRepositoryImpl

    @Before
    fun setUp() {
        fakeDreamRepositoryImpl = FakeDreamRepositoryImpl()
        getDreamsUseCase = GetDreamsUseCase(fakeDreamRepositoryImpl)

    }

    @Test
    fun ` get dreams returns empty list when no dreams exist`() = runBlocking {
        val dreams = getDreamsUseCase().first()

        assertTrue(dreams.isEmpty())
    }

    @Test
    fun `get dreams returns non-empty list`() = runBlocking {
        //arrange
        // Arrange: Add dreams to the repository
        val dream1 = DreamDomainModel(
            id = 1,
            title = "Dream 1",
            description = "Description of Dream 1",
            isFavorite = false,
            creationTime = ZonedDateTime.now(),
            imageUri = ""
        )
        val dream2 = DreamDomainModel(
            id = 2,
            title = "Dream 2",
            description = "Description of Dream 2",
            isFavorite = true,
            creationTime = ZonedDateTime.now(),
            imageUri = ""
        )
        fakeDreamRepositoryImpl.insertDream(dream1)
        fakeDreamRepositoryImpl.insertDream(dream2)

        //act
        val dreams = fakeDreamRepositoryImpl.getDreams().first()

        //assert
        assertFalse(dreams.isEmpty())
        assertEquals(2, dreams.size)
        assertTrue(dreams.contains(dream1))
        assertTrue(dreams.contains(dream2))
    }



}