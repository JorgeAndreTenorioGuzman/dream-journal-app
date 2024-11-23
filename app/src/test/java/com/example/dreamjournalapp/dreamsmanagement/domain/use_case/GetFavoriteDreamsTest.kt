package com.example.dreamjournalapp.dreamsmanagement.domain.use_case

import com.example.dreamjournalapp.dreamsmanagement.data.repository.FakeDreamRepositoryImpl
import com.example.dreamjournalapp.dreamsmanagement.domain.model.DreamDomainModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.time.ZonedDateTime

class GetFavoriteDreamsTest {

    private lateinit var fakeDreamRepositoryImpl: FakeDreamRepositoryImpl
    private lateinit var getFavoriteDreams: GetFavoriteDreams

    @Before
    fun setUp() {

        fakeDreamRepositoryImpl = FakeDreamRepositoryImpl()
        getFavoriteDreams = GetFavoriteDreams(fakeDreamRepositoryImpl)
    }

    @Test
    fun `get favorite dreams, obtain empty list if no favorite dreams`() = runBlocking {
        //act
       val favDreams = getFavoriteDreams().first()

        // assert
        assertTrue(favDreams.isEmpty())
    }

    @Test
    fun `get favorite dreams, obtain non-empty list if there are favorite dreams`() = runBlocking {
        //arrange
        fakeDreamRepositoryImpl.insertDream(
            DreamDomainModel(
                id = 1,
                title = "dream 1",
                description = "dream 1 description",
                creationTime = ZonedDateTime.now(),
                isFavorite = false,
                imageUri = ""
            )
        )
        fakeDreamRepositoryImpl.insertDream(
            DreamDomainModel(
                id = 2,
                title = "dream 2",
                description = "dream 2 description",
                creationTime = ZonedDateTime.now(),
                isFavorite = true,
                imageUri = ""
            )
        )

        //act
        val favDreams = getFavoriteDreams().first()

        //assert
        assertFalse(favDreams.isEmpty())
        assertEquals(1, favDreams.size)
    }
}
