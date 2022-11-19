package com.antonov.dailynotescalendar.data.room.repository

import com.antonov.dailynotescalendar.data.room.dao.NoteRoomDao
import com.antonov.dailynotescalendar.domain.model.*
import com.antonov.dailynotescalendar.domain.repository.INotesRepository
import java.util.*
import javax.inject.Inject

class RoomNotesRepository @Inject constructor(private val noteRoomDao: NoteRoomDao):
    INotesRepository {
    override suspend fun insertAllItems(items: List<Note>) {
        val result = mutableListOf<com.antonov.dailynotescalendar.data.room.model.Note>()
        for(item in items){
            result.add(com.antonov.dailynotescalendar.data.room.model.Note
                (0, item.date_start.time, item.name, item.description))
        }
        noteRoomDao.insertAll(result)
    }

    override suspend fun insert(item: Note) {
        noteRoomDao.insert(com.antonov.dailynotescalendar.data.room.model.Note
            (item.id?: 0, item.date_start.time, item.name, item.description))
    }

    override suspend fun getAllItems(): List<Note> {
        val items =  noteRoomDao.getAll()
        val result = mutableListOf<Note>()
        for(item in items){
            result.add(Note(item.id, Date(item.date_start), item.name, item.description))
        }
        return result
    }

    override suspend fun deleteItem(item: Note) {
        noteRoomDao.delete(com.antonov.dailynotescalendar.data.room.model.Note
            (item.id!!,item.date_start.time, item.name, item.description))
    }
}