package com.energyaustralia.codingtestsample


import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.allOf
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class E2ETest {

    @Test
    fun test001_verifyMainActivityLaunches() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.festival_list)).check(matches(isDisplayed()))
    }

    @Test
    fun test002_festivalListAppearsAndClickTheFirstElement() {
        ActivityScenario.launch(MainActivity::class.java)
        onData(allOf()).inAdapterView(withId(R.id.festival_list)).atPosition(0).perform(click())
    }
}