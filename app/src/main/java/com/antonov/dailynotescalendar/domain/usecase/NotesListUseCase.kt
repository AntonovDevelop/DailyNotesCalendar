package com.antonov.dailynotescalendar.domain.usecase

import com.antonov.dailynotescalendar.data.room.repository.RoomNotesRepository
import com.antonov.dailynotescalendar.domain.model.Note
import javax.inject.Inject

class NotesListUseCase @Inject constructor(
    private val roomNotesRepository: RoomNotesRepository
) {
    suspend fun getData(): List<Note>{
        return roomNotesRepository.getAllItems()
    }
    suspend fun addNote(note: Note){
        roomNotesRepository.insert(note)
    }
    suspend fun deleteNote(note: Note){
        roomNotesRepository.deleteItem(note)
    }
}