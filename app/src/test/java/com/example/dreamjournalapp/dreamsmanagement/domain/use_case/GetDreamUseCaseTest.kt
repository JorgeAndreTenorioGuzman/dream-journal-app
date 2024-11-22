package com.example.dreamjournalapp.dreamsmanagement.domain.use_case

import com.example.dreamjournalapp.dreamsmanagement.data.repository.FakeDreamRepositoryImpl
import com.example.dreamjournalapp.dreamsmanagement.domain.model.DreamDomainModel
import com.example.dreamjournalapp.dreamsmanagement.domain.model.DreamNotFoundException
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.time.ZonedDateTime

class GetDreamUseCaseTest {

    private lateinit var fakeDreamRepositoryImpl: FakeDreamRepositoryImpl
    private lateinit var getDreamUseCase: GetDreamUseCase

    @Before
    fun setUp() {
        fakeDreamRepositoryImpl = FakeDreamRepositoryImpl()
        getDreamUseCase = GetDreamUseCase(fakeDreamRepositoryImpl)

        runBlocking {

            fakeDreamRepositoryImpl.insertDream(
                DreamDomainModel(
                    id = 1,
                    title = "Dream 1",
                    description = "Dream 1 description",
                    creationTime = ZonedDateTime.now(),
                    isFavorite = false,
                    imageUri = ""
                )
            )

            fakeDreamRepositoryImpl.insertDream(
                DreamDomainModel(
                    id = 2,
                    title = "Dream 2",
                    description = "Dream 2 description",
                    creationTime = ZonedDateTime.now(),
                    isFavorite = true,
                    imageUri = ""
                )
            )

        }


    }

    @Test
    fun `get dream, obtain valid dream`() = runBlocking {
        //act
        val result = getDreamUseCase(1)

        //assert
        assertNotNull(result)
        assertEquals(1, result?.id)

    }


    @Test
    fun `get dream, throw exception if dream not found`() = runBlocking {

        val id = 999
        val exception = assertThrows(DreamNotFoundException::class.java) {
            runBlocking {
                getDreamUseCase(id)
            }
        }

        assertEquals("Dream with ID $id not found", exception.message)
    }
}