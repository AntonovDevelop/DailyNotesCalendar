package com.antonov.dailynotescalendar.di

import android.content.Context
import androidx.room.Room
import com.antonov.dailynotescalendar.data.room.AppRoomDatabase
import com.antonov.dailynotescalendar.data.room.dao.NoteRoomDao
import com.antonov.dailynotescalendar.data.room.repository.RoomNotesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun provideAppRoomDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, AppRoomDatabase::class.java,
        "Database"
    ).build()

    @Singleton
    @Provides
    fun provideNoteDao(db: AppRoomDatabase) = db.getNoteDao()

    @Provides
    @Singleton
    fun provideNotesRepository(noteRoomDao: NoteRoomDao): RoomNotesRepository {
        return RoomNotesRepository(noteRoomDao)
    }
}