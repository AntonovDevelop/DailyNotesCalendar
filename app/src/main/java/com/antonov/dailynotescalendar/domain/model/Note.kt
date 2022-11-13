package com.antonov.dailynotescalendar.domain.model

import java.util.*

data class Note(
    var id: Int?,
    val date_start: Date,
    val date_finish: Date,
    val name: String,
    var description: String){
    constructor(date_start: Date, date_finish: Date, name: String, description: String) :
            this(null, date_start, date_finish, name, description)
}