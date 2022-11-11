package com.antonov.dailynotescalendar.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.antonov.dailynotescalendar.data.room.dao.ItemRoomDao
import com.antonov.dailynotescalendar.data.room.model.Item

@Database(entities = [Item::class], version = 1)
abstract class AppRoomDatabase  : RoomDatabase(){

    abstract fun  getItemDao(): ItemRoomDao
}