package com.intact.newsbuff.ui.fragment

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.intact.newsbuff.R
import org.junit.Test
import org.junit.runner.RunWith

/**
 * FragmentScenario includes methods for launching the following types of fragments:
 * The methods also support the following types of fragments:
 *
 * 1) Graphical fragments, which contain a user interface. To launch this type of fragment, call
 * launchFragmentInContainer(). FragmentScenario attaches the fragment to an activity's root view
 * controller. This containing activity is otherwise empty.
 *
 * 2) Non-graphical fragments (sometimes referred to as headless fragments), which store or perform
 * short-term processing on information included in several activities. To launch this type of
 * fragment, call launchFragment(). FragmentScenario attaches this type of fragment to an entirely
 * empty activity, one that doesn't have a root view.
 *
 * https://developer.android.com/training/basics/fragments/testing
 */

@RunWith(AndroidJUnit4ClassRunner::class)
class HomeFragmentTest {

    @Test
    fun test_progress_bar_visible() {
        launchFragmentInContainer<HomeFragment>()
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()))
    }
}