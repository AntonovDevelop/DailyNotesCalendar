package com.antonov.dailynotescalendar.domain.model

import java.util.*

data class Note(
    var id: Int?,
    val date_start: GregorianCalendar,
    val date_finish: GregorianCalendar,
    val name: String,
    var description: String){
    constructor(date_start: GregorianCalendar, date_finish: GregorianCalendar, name: String, description: String) :
            this(null, date_start, date_finish, name, description)
}