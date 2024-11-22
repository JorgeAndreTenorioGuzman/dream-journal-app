package com.example.dreamjournalapp.dreamsmanagement.domain.use_case

import com.example.dreamjournalapp.dreamsmanagement.data.repository.FakeDreamRepositoryImpl
import com.example.dreamjournalapp.dreamsmanagement.domain.model.DreamDomainModel
import com.example.dreamjournalapp.dreamsmanagement.domain.model.InvalidDreamException
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.time.ZonedDateTime


class AddDreamToFavouritesTest{

    private  lateinit var addDreamToFavourites: AddDreamToFavourites
    private lateinit var fakeDreamRepositoryImpl: FakeDreamRepositoryImpl

    @Before
    fun setUp(){
        fakeDreamRepositoryImpl = FakeDreamRepositoryImpl()
        addDreamToFavourites = AddDreamToFavourites(fakeDreamRepositoryImpl)

        //Add initial dreams to repository
        runBlocking {
            fakeDreamRepositoryImpl.insertDream(
                DreamDomainModel(
                    id = 1,
                    title = "Dream 1",
                    description = "Dream 1 description",
                    isFavorite = false,
                    creationTime = ZonedDateTime.now(),
                    imageUri = ""
                )
            )
            fakeDreamRepositoryImpl.insertDream(
                DreamDomainModel(
                    id = 2,
                    title = "Dream 2",
                    description = "Dream 2 description",
                    isFavorite = true,
                    creationTime = ZonedDateTime.now(),
                    imageUri = ""
                )
            )
        }
    }

    @Test
    fun `toggle favorite status for valid dream, succeeds`() = runBlocking {
        //act
        addDreamToFavourites(1, true)

        //assert
        val updatedDream = fakeDreamRepositoryImpl.getDreamById(1)
        assertNotNull(updatedDream)
        assertTrue(updatedDream!!.isFavorite)
    }

    @Test
    fun `set favorite status to the same value does nothing`() = runBlocking {
        //act
        addDreamToFavourites(2,true)

        //assert
        val dream = fakeDreamRepositoryImpl.getDreamById(2)
        assertNotNull(dream)
        assertTrue(dream!!.isFavorite)
    }

    @Test
    fun `set dream with null id throws exception`() = runBlocking {
        //act and assert
        val exception = assertThrows(InvalidDreamException::class.java){
            runBlocking {
                addDreamToFavourites(0, true)
            }
        }

        assertEquals("Dream id must be positive number", exception.message)
    }

    @Test
    fun `set dream with invalid id throws exception`() = runBlocking {
        //act and assert
        val exception = assertThrows(InvalidDreamException::class.java){
            runBlocking {
                addDreamToFavourites(-1, true)
            }
        }

        assertEquals("Dream id must be positive number", exception.message)
    }

    @Test
    fun `set favorite status for non-exisitng dream does nothing`() = runBlocking {
        //act
        addDreamToFavourites(999, true)

        //assert
        val dream = fakeDreamRepositoryImpl.getDreamById(999)
        assertNull(dream)
    }


}