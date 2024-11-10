package com.example.dreamjournalapp.dreamsmanagement.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dreamjournalapp.dreamsmanagement.data.model.DreamEntity

@Database(
    entities = [DreamEntity::class],
    version = 1
)
abstract class DreamDatabase: RoomDatabase() {
    abstract val dreamDao: DreamDao

    companion object{
        const val DATABASE_NAME = "dreams_db"
    }
}