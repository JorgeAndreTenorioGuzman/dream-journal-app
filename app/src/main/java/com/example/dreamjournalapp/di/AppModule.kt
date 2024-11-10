package com.example.dreamjournalapp.di

import android.app.Application
import androidx.room.Room
import com.example.dreamjournalapp.dreamsmanagement.data.local.DreamDatabase
import com.example.dreamjournalapp.dreamsmanagement.data.repository.DreamRepositoryImpl
import com.example.dreamjournalapp.dreamsmanagement.domain.repository.DreamRepository
import com.example.dreamjournalapp.dreamsmanagement.domain.use_case.AddDreamUseCase
import com.example.dreamjournalapp.dreamsmanagement.domain.use_case.DreamUsesCases
import com.example.dreamjournalapp.dreamsmanagement.domain.use_case.GetDreamUseCase
import com.example.dreamjournalapp.dreamsmanagement.domain.use_case.GetDreamsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDreamDatabase(app: Application):DreamDatabase {
        return Room.databaseBuilder(
            app,
            DreamDatabase::class.java,
            DreamDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideDreamRepository(db: DreamDatabase): DreamRepository{
        return DreamRepositoryImpl(db.dreamDao)
    }

    @Provides
    @Singleton
    fun provideDreamUsesCases(repository: DreamRepository): DreamUsesCases {
        return DreamUsesCases(
            getDreams = GetDreamsUseCase(repository),
            addDream = AddDreamUseCase(repository),
            getDream = GetDreamUseCase(repository)
        )
    }
}