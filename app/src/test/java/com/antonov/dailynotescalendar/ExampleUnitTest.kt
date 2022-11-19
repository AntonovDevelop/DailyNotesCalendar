package com.antonov.dailynotescalendar

import com.antonov.dailynotescalendar.domain.usecase.TimeConvert
import org.junit.Test

import org.junit.Assert.*
import java.util.*

class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun test_note(){
        val calendar = TimeConvert.getCalendar(12, 11, 2022)
        assertEquals(calendar.get(Calendar.YEAR), 2022)
    }
}