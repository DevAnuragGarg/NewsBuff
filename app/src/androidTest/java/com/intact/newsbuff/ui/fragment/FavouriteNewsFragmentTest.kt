package com.intact.newsbuff.ui.fragment

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.intact.newsbuff.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class FavouriteNewsFragmentTest() {

    @Test
    fun test_progress_bar_visible() {
        launchFragmentInContainer<FavouriteNewsFragment>()
        onView(ViewMatchers.withId(R.id.customView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}