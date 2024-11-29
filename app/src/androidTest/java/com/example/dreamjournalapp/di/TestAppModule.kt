package com.example.dreamjournalapp.di

import android.app.Application
import androidx.room.Room
import com.example.dreamjournalapp.dreamsmanagement.data.local.DreamDatabase
import com.example.dreamjournalapp.dreamsmanagement.data.repository.DreamRepositoryImpl
import com.example.dreamjournalapp.dreamsmanagement.data.repository.FakeDreamRepositoryImpl
import com.example.dreamjournalapp.dreamsmanagement.domain.repository.DreamRepository
import com.example.dreamjournalapp.dreamsmanagement.domain.use_case.AddDreamToFavourites
import com.example.dreamjournalapp.dreamsmanagement.domain.use_case.AddDreamUseCase
import com.example.dreamjournalapp.dreamsmanagement.domain.use_case.DreamUsesCases
import com.example.dreamjournalapp.dreamsmanagement.domain.use_case.GetDreamUseCase
import com.example.dreamjournalapp.dreamsmanagement.domain.use_case.GetDreamsUseCase
import com.example.dreamjournalapp.dreamsmanagement.domain.use_case.GetFavoriteDreams
import com.example.dreamjournalapp.dreamsmanagement.presentation.dreams.DreamsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Singleton
    fun provideDreamDatabase(app: Application):DreamDatabase {
        return Room.inMemoryDatabaseBuilder(
            app,
            DreamDatabase::class.java,
        ).build()
    }

    @Provides
    @Singleton
    fun provideDreamRepository(db: DreamDatabase): DreamRepository{
        return FakeDreamRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideDreamUsesCases(repository: DreamRepository): DreamUsesCases {
        return DreamUsesCases(
            getDreams = GetDreamsUseCase(repository),
            addDream = AddDreamUseCase(repository),
            getDream = GetDreamUseCase(repository),
            addDreamToFavourites = AddDreamToFavourites(repository),
            getFavoriteDreams = GetFavoriteDreams(repository)
        )
    }

}