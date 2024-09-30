package com.example.kotlin_multi_screen_app_basic.Utils

import android.graphics.Rect
import android.util.Log
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.ListView
import android.widget.ScrollView
import androidx.core.widget.NestedScrollView
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.util.HumanReadables
import org.hamcrest.Matcher
import org.hamcrest.Matchers

class NestedScrollToAction : ViewAction {

    override fun getConstraints(): Matcher<View> = Matchers.allOf(
        ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
        ViewMatchers.isDescendantOfA(
            Matchers.anyOf(
                ViewMatchers.isAssignableFrom(ScrollView::class.java),
                ViewMatchers.isAssignableFrom(HorizontalScrollView::class.java),
                ViewMatchers.isAssignableFrom(ListView::class.java),
                ViewMatchers.isAssignableFrom(NestedScrollView::class.java)
            )
        )
    )

    override fun perform(uiController: UiController, view: View) {
        if (ViewMatchers.isDisplayingAtLeast(PERCENT_90).matches(view)) {
            Log.i(TAG, "View is already displayed. Returning.")
            return
        }

        val rect = Rect()
        view.getDrawingRect(rect)
        if (!view.requestRectangleOnScreen(rect, true /* immediate */)) {
            Log.w(TAG, "Scrolling to view was requested, but none of the parents scrolled.")
        }

        uiController.loopMainThreadUntilIdle()
        if (!ViewMatchers.isDisplayingAtLeast(PERCENT_90).matches(view)) {
            throw PerformException.Builder()
                .withActionDescription(this.description)
                .withViewDescription(HumanReadables.describe(view))
                .withCause(RuntimeException("Scrolling to view was attempted, but the view is not displayed"))
                .build()
        }
    }

    override fun getDescription(): String = "scroll to"

    companion object {
        private val TAG = NestedScrollToAction::class.java.simpleName
        const val PERCENT_90 = 90
    }
}