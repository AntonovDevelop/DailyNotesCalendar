package com.antonov.dailynotescalendar.presentation

import androidx.fragment.app.testing.FragmentScenario
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.antonov.dailynotescalendar.presentation.mainFragments.CalendarFragment
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class FragmentCalendarTest {
    private lateinit var scenario: FragmentScenario<CalendarFragment>
    private lateinit var scenarioActivity: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenarioActivity = ActivityScenario.launch(MainActivity::class.java)

        scenario = FragmentScenario.launch(CalendarFragment::class.java)
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun testIsVisible() {
        //onView(withId(R.id.calendarView)).check(matches(isDisplayed()))
    }
}