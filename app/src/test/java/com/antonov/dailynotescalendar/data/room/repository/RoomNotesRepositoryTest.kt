package com.antonov.dailynotescalendar.data.room.repository

import com.antonov.dailynotescalendar.domain.model.Note
import com.antonov.dailynotescalendar.domain.repository.INotesRepository
import org.junit.Assert.*

class FakeNotesRepositoryTest: INotesRepository{
    override suspend fun insertAllItems(items: List<Note>) {
        TODO("Not yet implemented")
    }

    override suspend fun insert(item: Note) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllItems(): List<Note> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteItem(item: Note) {
        TODO("Not yet implemented")
    }

}