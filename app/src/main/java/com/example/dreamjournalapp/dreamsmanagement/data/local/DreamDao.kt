package com.example.dreamjournalapp.dreamsmanagement.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dreamjournalapp.dreamsmanagement.data.model.DreamEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DreamDao {

    @Query("Select * FROM dreamentity")
    fun getDreams(): Flow<List<DreamEntity>>

    @Query("SELECT * FROM dreamentity WHERE id = :id")
    suspend fun getDreamById(id: Int): DreamEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDream(dream: DreamEntity)

    @Delete
    suspend fun deleteDream(dream: DreamEntity)
}