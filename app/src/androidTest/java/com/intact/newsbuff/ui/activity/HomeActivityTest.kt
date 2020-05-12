package com.intact.newsbuff.ui.activity

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.intact.newsbuff.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class HomeActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun check_toolbar_visible() {
        onView(ViewMatchers.withId(R.id.toolBar)).check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun check_bottom_navigation_visible() {
        onView(ViewMatchers.withId(R.id.bottom_navigation_view)).check(
            ViewAssertions.matches(
                isDisplayed()
            )
        )
    }

    @Test
    fun click_second_fragment_bottom_navigation() {
        onView(ViewMatchers.withId(R.id.bottom_navigation_view)).check(
            ViewAssertions.matches(
                isDisplayed()
            )
        )
    }
}
