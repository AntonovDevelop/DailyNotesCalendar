package com.antonov.dailynotescalendar.data.room.dao

import androidx.room.*
import com.antonov.dailynotescalendar.data.room.model.Note

@Dao
interface NoteRoomDao {

    @Query("SELECT * FROM item")
    fun getAll(): List<Note>

    @Query("SELECT * FROM item WHERE id IN (:itemIds)")
    fun loadAllByIds(itemIds: IntArray): List<Note>

    @Query("SELECT * FROM item WHERE description LIKE :description LIMIT 1")
    fun findByName(description: String): Note

    @Insert
    fun insertAll(items: List<Note>)

    @Insert
    fun insert(item: Note)

    @Delete
    fun delete(item: Note)
}