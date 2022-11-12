package com.antonov.dailynotescalendar.di

import com.antonov.dailynotescalendar.data.room.repository.RoomNotesRepository
import com.antonov.dailynotescalendar.domain.usecase.NotesListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    @Singleton
    fun provideNotesListUseCase(roomNotesRepository: RoomNotesRepository): NotesListUseCase {
        return NotesListUseCase(roomNotesRepository)
    }
}