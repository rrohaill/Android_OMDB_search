package io.rohail.androidomdbsearch.activities

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import io.rohail.androidomdbsearch.R
import io.rohail.androidomdbsearch.ui.detail.view.DetailActivity
import io.rohail.androidomdbsearch.ui.search.view.SearchActivity
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class SearchActivityTest {

    @Before
    fun lunchMainActivity() {
        val mainActivity = ActivityTestRule(SearchActivity::class.java)
        mainActivity.launchActivity(Intent())
    }

    @Test
    fun searchBarTest() {
        val searchB = onView(withId(R.id.searchButton))
        val searchET =
            onView(Matchers.allOf(withId(R.id.searchInput), withText("")))

        try {
            searchET.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        } catch (e: Exception) {
            throw Exception("Search field with an empty area does not exist")
        }
        try {
            searchB.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        } catch (e: Exception) {
            throw Exception("Search button does not exist")
        }
    }

    @Test
    fun noResultsFoundMessageIsHiddenTest() {
        try {
            onView(
                Matchers.allOf(
                    withId(R.id.noResultMessage),
                    withText(R.string.no_results_found)
                )
            )
                .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        } catch (e: Exception) {
            throw Exception("No-results-found message is visible")
        }
    }

    @Test
    fun recyclerViewIsLoadedWithEmptyListTest() {
        val recyclerView = onView(allOf(withId(R.id.recyclerView)))
        recyclerView.check { view, noViewFoundException ->
            noViewFoundException?.apply {
                throw this
            }
            Assert.assertTrue(
                "Recyclerview is not empty from the beginning",
                view is RecyclerView && view.adapter?.itemCount == 0
            )
        }
    }

    @Test
    fun searchFunctionalityTest() {
        val searchInputET = onView(withId(R.id.searchInput))
        val searchB = onView(withId(R.id.searchButton))
        val recyclerView = onView(allOf(withId(R.id.recyclerView)))
        searchInputET.perform(ViewActions.clearText(), ViewActions.typeText("Marvel"))
        searchB.perform(ViewActions.click())
        onView(ViewMatchers.isRoot()).perform(InstrumentTestHelper.waitFor(3000))
        recyclerView.check { view, noViewFoundException ->
            noViewFoundException?.apply {
                throw this
            }
            Assert.assertTrue(
                "Recyclerview is empty after the API call",
                view is RecyclerView && view.adapter?.itemCount != 0
            )
        }
    }

    @Test
    fun recyclerViewClickStartsDetailActivityTest() {
        Intents.init()
        val searchInputET = onView(withId(R.id.searchInput))
        val searchB = onView(withId(R.id.searchButton))
        val recyclerView = onView(allOf(withId(R.id.recyclerView)))
        searchInputET.perform(ViewActions.clearText(), ViewActions.typeText("Marvel"))
        searchB.perform(ViewActions.click())
        onView(ViewMatchers.isRoot()).perform(InstrumentTestHelper.waitFor(3000))
        recyclerView
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    ViewActions.click()
                )
            )
        intended(hasComponent(DetailActivity::class.java.name))
    }

}