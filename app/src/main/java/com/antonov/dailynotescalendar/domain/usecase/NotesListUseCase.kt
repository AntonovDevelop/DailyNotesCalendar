package com.antonov.dailynotescalendar.domain.usecase

import com.antonov.dailynotescalendar.domain.model.Note
import com.antonov.dailynotescalendar.domain.repository.INotesRepository
import javax.inject.Inject

class NotesListUseCase @Inject constructor(
    private val roomNotesRepository: INotesRepository
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