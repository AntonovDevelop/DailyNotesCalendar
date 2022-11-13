package com.antonov.dailynotescalendar.domain.model

data class Hour (
    val hours: String,
    var note: Note?
){
    constructor(hours: String) :
            this(hours, null)
}