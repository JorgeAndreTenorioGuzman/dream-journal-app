package com.example.dreamjournalapp.dreamsmanagement.domain.use_case

import com.example.dreamjournalapp.dreamsmanagement.data.repository.FakeDreamRepositoryImpl
import com.example.dreamjournalapp.dreamsmanagement.domain.model.DreamDomainModel
import com.example.dreamjournalapp.dreamsmanagement.domain.model.InvalidDreamException
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.time.ZonedDateTime

class AddDreamUseCaseTest {

    private lateinit var addDream: AddDreamUseCase
    private lateinit var fakeDreamRepositoryImpl: FakeDreamRepositoryImpl

    @Before
    fun setUp() {
        fakeDreamRepositoryImpl = FakeDreamRepositoryImpl()
        addDream = AddDreamUseCase(fakeDreamRepositoryImpl)
    }

    @Test
    fun `add dream with valid input succeeds`() = runBlocking {
        // Arrange
        val dream = DreamDomainModel(
            id = 1,
            title = "test-title1",
            description = "This is dresciption of test dream 1",
            isFavorite = false,
            creationTime = ZonedDateTime.now(),
            imageUri = ""
        )

        // Act
        addDream(dream)

        //Assert
        val allDreams = fakeDreamRepositoryImpl.getDreams()
        assertTrue(allDreams.map { list -> list.contains(dream)}.first())
    }

    @Test
    fun `add dream with empty title throws exception`() = runBlocking {
        //Arrange
        val dream = DreamDomainModel(
            id = 2,
            title = "",
            description = "This is dresciption of the dream",
            isFavorite = false,
            creationTime = ZonedDateTime.now(),
            imageUri = ""
        )

        //Act & Assert
        val exception = assertThrows(InvalidDreamException::class.java){
            runBlocking {
                addDream(dream)
            }
        }

        assertEquals("The title of the dream can't be empty", exception.message)
    }

    @Test
    fun `add dream with empty description throws exception`() = runBlocking {
        //Arrange
        val dream = DreamDomainModel(
            id = 3,
            title = "dream test 3",
            description = "",
            isFavorite = false,
            creationTime = ZonedDateTime.now(),
            imageUri = ""
        )

        //Act & Assert
        val exception = assertThrows(InvalidDreamException::class.java){
            runBlocking {
                addDream(dream)
            }
        }

        assertEquals("The description of the dream can't be empty", exception.message)

    }

}