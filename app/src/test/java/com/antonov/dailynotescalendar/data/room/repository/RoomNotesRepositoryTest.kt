package com.antonov.dailynotescalendar.data.room.repository

import com.antonov.dailynotescalendar.domain.model.Note
import com.antonov.dailynotescalendar.domain.repository.INotesRepository
import org.junit.Assert.*

class FakeNotesRepositoryTest: INotesRepository{
    private val repository = mutableListOf<Note>()
    override suspend fun insertAllItems(items: List<Note>) {
        repository.addAll(items)
    }

    override suspend fun insert(item: Note) {
        repository.add(item)
    }

    override suspend fun getAllItems(): List<Note> {
        return repository
    }

    override suspend fun deleteItem(item: Note) {
        repository.remove(item)
    }

}