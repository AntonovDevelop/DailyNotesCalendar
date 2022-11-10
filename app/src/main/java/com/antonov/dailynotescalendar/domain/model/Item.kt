package com.antonov.dailynotescalendar.domain.model

class Item {
    var id = 0
    var name: String
    var size: String
    var isChecked: Boolean

    constructor(id: Int, name: String, size: String, isChecked: Boolean) {
        this.id = id
        this.name = name
        this.size = size
        this.isChecked = isChecked
    }

    constructor(name: String, size: String, isChecked: Boolean) {
        this.name = name
        this.size = size
        this.isChecked = isChecked
    }
}