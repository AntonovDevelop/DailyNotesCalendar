package com.antonov.dailynotescalendar.domain.model

import java.util.*

data class Note(
    var id: Int?,
    var date_start: Date,
    var date_finish: Date,
    var name: String,
    var description: String){
    constructor(date_start: Date, date_finish: Date, name: String, description: String) :
            this(null, date_start, date_finish, name, description)
}