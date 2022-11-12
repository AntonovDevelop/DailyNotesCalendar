package com.antonov.dailynotescalendar.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.antonov.dailynotescalendar.data.room.dao.NoteRoomDao
import com.antonov.dailynotescalendar.data.room.model.Note

@Database(entities = [Note::class], version = 1)
abstract class AppRoomDatabase  : RoomDatabase(){

    abstract fun  getNoteDao(): NoteRoomDao
}