package io.rohail.androidomdbsearch.activities

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

class InstrumentTestHelper {
    companion object {

        /**
         * Perform action of waiting for a specific time.
         * @param time amount of time to wait in Milliseconds
         * @return ViewAction
         */
        fun waitFor(time: Int): ViewAction {
            return object : ViewAction {
                override fun getConstraints(): Matcher<View> {
                    return ViewMatchers.isRoot()
                }

                override fun getDescription(): String {
                    return "Waiting for $time milliseconds"
                }

                override fun perform(uiController: UiController, view: View?) {
                    uiController.loopMainThreadForAtLeast(time.toLong())
                }
            }
        }

        /**
         * Matches drawable with provided resource ID
         * @param resourceId Int Drawable resource ID
         * @return the Matcher instance
         */
        fun withDrawable(resourceId: Int): Matcher<View?> {
            return DrawableMatcher(resourceId)
        }

        /**
         * Matches tha drawable is empty
         * @return the Matcher instance
         */
        fun noDrawable(): Matcher<View?> {
            return DrawableMatcher(-1)
        }
    }

    internal class DrawableMatcher(resourceId: Int) : TypeSafeMatcher<View?>() {
        override fun matchesSafely(item: View?): Boolean {
            return false
        }

        override fun describeTo(description: Description?) {}
    }
}