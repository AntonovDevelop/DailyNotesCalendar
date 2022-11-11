package com.antonov.dailynotescalendar.data.room.repository

import com.antonov.dailynotescalendar.data.room.dao.ItemRoomDao
import com.antonov.dailynotescalendar.domain.model.*
import javax.inject.Inject

class RoomRepository @Inject constructor(private val itemRoomDao: ItemRoomDao) {
    suspend fun insertAllItems(items: List<Item>) {
        val result = mutableListOf<com.antonov.dailynotescalendar.data.room.model.Item>()
        for(item in items){
            result.add(com.antonov.dailynotescalendar.data.room.model.Item(0, item.description))
        }
        itemRoomDao.insertAll(result)
    }

    suspend fun getAllItems(): List<Item> {
        val items =  itemRoomDao.getAll()
        val result = mutableListOf<Item>()
        for(item in items){
            result.add(Item(item.id,item.description))
        }
        return result
    }

    suspend fun deleteItem(item: Item) {
        itemRoomDao.delete(com.antonov.dailynotescalendar.data.room.model.Item(item.id!!,item.description))
    }
}