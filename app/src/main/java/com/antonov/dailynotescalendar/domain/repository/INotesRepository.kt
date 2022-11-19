package com.antonov.dailynotescalendar.domain.repository

import com.antonov.dailynotescalendar.domain.model.Note

interface INotesRepository {
    suspend fun insertAllItems(items: List<Note>)

    suspend fun insert(item: Note)

    suspend fun getAllItems(): List<Note>

    suspend fun deleteItem(item: Note)
}