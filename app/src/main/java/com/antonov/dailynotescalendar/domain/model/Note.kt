package com.antonov.dailynotescalendar.domain.model

data class Note(
    var id: Int?,
    val date_start: String,
    val date_finish: String,
    val name: String,
    var description: String){
    constructor(date_start: String, date_finish: String, name: String, description: String) :
            this(null, date_start, date_finish, name, description)
}