package com.antonov.dailynotescalendar.data.room.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.antonov.dailynotescalendar.data.room.model.Note

@Dao
interface NoteRoomDao {

    @Query("SELECT * FROM note")
    suspend fun getAll(): List<Note>

    @Query("SELECT * FROM note WHERE id IN (:itemIds)")
    suspend fun loadAllByIds(itemIds: IntArray): List<Note>

    @Query("SELECT * FROM note WHERE name LIKE :name LIMIT 1")
    suspend fun findByName(name: String): Note

    @Insert
    suspend fun insertAll(items: List<Note>)

    @Insert(onConflict = REPLACE)
    suspend fun insert(item: Note)

    @Delete
    suspend fun delete(item: Note)
}