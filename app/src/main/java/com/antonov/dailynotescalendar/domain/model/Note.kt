package com.antonov.dailynotescalendar.domain.model

data class Note(
    var id: Int?,
    var description: String){
    constructor(description: String) : this(null, description)
}