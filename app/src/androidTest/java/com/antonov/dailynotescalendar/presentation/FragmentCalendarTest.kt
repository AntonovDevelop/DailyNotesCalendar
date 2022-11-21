package com.antonov.dailynotescalendar.presentation

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.antonov.dailynotescalendar.R
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class FragmentCalendarTest {
    @BindValue
    @JvmField
    val viewModel = mockk<MainViewModel>(relaxed = true)

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
    }

    @Test
    fun test_Display_CalendarView() {
        val scenario = launchActivity<MainActivity>()
        onView(withId(R.id.calendarView)).check(matches(isDisplayed()))
    }
    @Test
    fun test_Click_Button() {
        val scenario = launchActivity<MainActivity>()
        onView(withId(R.id.fab)).perform(click())
    }
}