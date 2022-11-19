package com.antonov.dailynotescalendar.presentation

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.antonov.dailynotescalendar.R
import org.junit.Test

import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @Test
    fun testActivity_inView() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.nav_host_fragment_content_main)).check(matches(isDisplayed()))
    }

    // Visibility
    @Test
    fun testVisibility_title_nextButton() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.calendarView))
            .check(matches(isDisplayed())) // method 1

        onView(withId(R.id.calendarView))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE))) // method 2
    }
}