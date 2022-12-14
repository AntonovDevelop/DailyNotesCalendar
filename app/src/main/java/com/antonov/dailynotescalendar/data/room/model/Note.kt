package com.antonov.dailynotescalendar.data.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val date_start: Long,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val description: String,
)