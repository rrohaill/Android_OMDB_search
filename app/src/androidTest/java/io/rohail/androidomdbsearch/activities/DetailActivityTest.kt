package io.rohail.androidomdbsearch.activities

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import io.rohail.androidomdbsearch.R
import io.rohail.androidomdbsearch.ui.search.view.SearchActivity
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)

class DetailActivityTest {

    @Before
    fun lunchDetailsActivity() {
        val mainActivity = ActivityTestRule(SearchActivity::class.java)
        mainActivity.launchActivity(Intent())
        val searchInputET = onView(withId(R.id.searchInput))
        val searchB = onView(withId(R.id.searchButton))
        val recyclerView = onView(allOf(withId(R.id.recyclerView)))
        searchInputET.perform(clearText(), typeText("Marvel"))
        searchB.perform(click())
        onView(isRoot()).perform(InstrumentTestHelper.waitFor(3000))
        recyclerView
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )
    }

    @Test
    fun movieNameIsDisplayedTest() {
        try {
            onView(withId(R.id.tvTitle)).check(matches(not(withText(""))))
            onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        } catch (e: Exception) {
            throw Exception("Movie title is empty")
        }
    }

    @Test
    fun moviePosterIsDisplayedTest() {
        try {
            onView(withId(R.id.ivIcon)).check(matches(not(InstrumentTestHelper.noDrawable())))
        } catch (e: Exception) {
            throw Exception("Movie poster is empty")
        }
    }
}