package com.antonov.dailynotescalendar.domain.usecase

import org.junit.Assert.*
import org.junit.Test
import java.util.*

class TimeConvertTest{
    @Test
    fun `test time year`(){
        val calendar = TimeConvert.getCalendar(12, 11, 2022)
        assertEquals(calendar.get(Calendar.YEAR), 2022)
    }
    @Test
    fun `test time month`(){
        val calendar = TimeConvert.getCalendar(12, 11, 2022)
        assertEquals(calendar.get(Calendar.MONTH), 11)
    }
    @Test
    fun `test time day`(){
        val calendar = TimeConvert.getCalendar(12, 11, 2022)
        assertEquals(calendar.get(Calendar.DAY_OF_MONTH), 12)
    }
    @Test
    fun `test timestamp`(){
        val timestamp = TimeConvert.getLong(21, 11, 2022, 13, 22, 17)
        assertEquals(	1671614537000, timestamp)
    }
}