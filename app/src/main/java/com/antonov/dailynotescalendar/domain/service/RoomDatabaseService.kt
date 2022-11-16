package com.antonov.dailynotescalendar.domain.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.antonov.dailynotescalendar.data.room.repository.RoomNotesRepository
import com.antonov.dailynotescalendar.domain.model.Note
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class RoomDatabaseService : Service() {
    private val binder = LocalBinder()

    inner class LocalBinder : Binder() {
        fun getService(): RoomDatabaseService = this@RoomDatabaseService
    }
    @Inject
    lateinit var roomNotesRepository: RoomNotesRepository

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

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