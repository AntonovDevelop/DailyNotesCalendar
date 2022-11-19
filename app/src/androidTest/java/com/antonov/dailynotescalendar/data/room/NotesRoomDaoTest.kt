package com.antonov.dailynotescalendar.data.room

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.antonov.dailynotescalendar.data.room.dao.NoteRoomDao
import com.antonov.dailynotescalendar.data.room.model.Note
import com.antonov.dailynotescalendar.domain.usecase.TimeConvert
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class NotesRoomDaoTest {
    private lateinit var noteRoomDao: NoteRoomDao
    private lateinit var db: AppRoomDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppRoomDatabase::class.java).build()
        noteRoomDao = db.getNoteDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeNoteAndReadByName(): Unit = runBlocking {
        val note = Note(0, TimeConvert.getLong(19,11,2022), "task1", "description1")
        noteRoomDao.insert(note)
        val noteFromDao = noteRoomDao.findByName("task1")
        assertThat(noteFromDao.description == "description1")
    }

    @Test
    @Throws(Exception::class)
    fun writeNoteAndReadInList(): Unit = runBlocking {
        val note = Note(0, TimeConvert.getLong(19,11,2022), "task1", "description1")
        noteRoomDao.insert(note)
        val noteFromDao = noteRoomDao.getAll()
        assertThat(noteFromDao.isNotEmpty())
    }
}