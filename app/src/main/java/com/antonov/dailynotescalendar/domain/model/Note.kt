package com.antonov.dailynotescalendar.domain.model

import java.util.*

//заметка
data class Note(
    var id: Int?,
    var date_start: Date,
    var name: String,
    var description: String){
    constructor(date_start: Date, name: String, description: String) :
            this(null, date_start, name, description)
}