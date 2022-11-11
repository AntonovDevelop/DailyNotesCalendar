package com.antonov.dailynotescalendar.data.room.dao

import androidx.room.*
import com.antonov.dailynotescalendar.data.room.model.Item

@Dao
interface ItemRoomDao {

    @Query("SELECT * FROM item")
    fun getAll(): List<Item>

    @Query("SELECT * FROM item WHERE id IN (:itemIds)")
    fun loadAllByIds(itemIds: IntArray): List<Item>

    @Query("SELECT * FROM item WHERE description LIKE :description LIMIT 1")
    fun findByName(description: String): Item

    @Insert
    fun insertAll(items: List<Item>)

    @Delete
    fun delete(item: Item)
}