package com.antonov.dailynotescalendar.data.room.repository

import com.antonov.dailynotescalendar.data.room.dao.NoteRoomDao
import com.antonov.dailynotescalendar.domain.model.*
import javax.inject.Inject

class RoomNotesRepository @Inject constructor(private val noteRoomDao: NoteRoomDao) {
    suspend fun insertAllItems(items: List<Note>) {
        val result = mutableListOf<com.antonov.dailynotescalendar.data.room.model.Note>()
        for(item in items){
            result.add(com.antonov.dailynotescalendar.data.room.model.Note
                (0, item.date_start, item.date_finish, item.name, item.description))
        }
        noteRoomDao.insertAll(result)
    }

    suspend fun insert(item: Note) {
        noteRoomDao.insert(com.antonov.dailynotescalendar.data.room.model.Note
            (item.id?: 0, item.date_start, item.date_finish, item.name, item.description))
    }

    suspend fun getAllItems(): List<Note> {
        val items =  noteRoomDao.getAll()
        val result = mutableListOf<Note>()
        for(item in items){
            result.add(Note(item.id, item.date_start, item.date_finish, item.name, item.description))
        }
        return result
    }

    suspend fun deleteItem(item: Note) {
        noteRoomDao.delete(com.antonov.dailynotescalendar.data.room.model.Note
            (item.id!!,item.date_start, item.date_finish, item.name, item.description))
    }
}