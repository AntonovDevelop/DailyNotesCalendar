package com.antonov.dailynotescalendar.domain.usecase

import java.util.*

object TimeConvert {
    fun getCalendar(day: Int, month: Int, year: Int): Calendar{
        return GregorianCalendar(year, month, day )
    }
    fun getCalendar(day: Int, month: Int, year: Int, hour: Int, minute: Int): Calendar{
        return GregorianCalendar(year, month, day, hour, minute )
    }
    fun getCalendar(date: Date): Calendar{
        val calendar = GregorianCalendar()
        calendar.time = date
        return calendar
    }
    fun getDate(day: Int, month: Int, year: Int): Date{
        val calendar = GregorianCalendar(year, month, day)
        return calendar.time
    }
    fun getDate(day: Int, month: Int, year: Int, hour: Int, minute: Int): Date{
        val calendar = GregorianCalendar(year, month, day, hour, minute)
        return calendar.time
    }
    fun getDate(calendar: Calendar): Date{
        return calendar.time
    }
    fun getLong(day: Int, month: Int, year: Int): Long{
        val calendar = GregorianCalendar(year, month, day)
        return calendar.time.time
    }
    fun getLong(day: Int, month: Int, year: Int, hour: Int, minute: Int): Long{
        val calendar = GregorianCalendar(year, month, day, hour, minute)
        return calendar.time.time
    }
    fun getLong(calendar: Calendar): Long{
        return calendar.time.time
    }
}