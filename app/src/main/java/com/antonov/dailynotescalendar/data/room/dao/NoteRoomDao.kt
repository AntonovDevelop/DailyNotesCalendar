package com.antonov.dailynotescalendar.data.room.dao

import androidx.room.*
import com.antonov.dailynotescalendar.data.room.model.Note

@Dao
interface NoteRoomDao {

    @Query("SELECT * FROM note")
    suspend fun getAll(): List<Note>

    @Query("SELECT * FROM note WHERE id IN (:itemIds)")
    suspend fun loadAllByIds(itemIds: IntArray): List<Note>

    @Query("SELECT * FROM note WHERE description LIKE :description LIMIT 1")
    suspend fun findByName(description: String): Note

    @Insert
    suspend fun insertAll(items: List<Note>)

    @Insert
    suspend fun insert(item: Note)

    @Delete
    suspend fun delete(item: Note)
}