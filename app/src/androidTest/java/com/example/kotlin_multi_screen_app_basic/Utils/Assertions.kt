package com.example.kotlin_multi_screen_app_basic.Utils

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.core.StringContains.containsString
import org.junit.Assert.assertTrue

fun ViewInteraction.isDisplayed(): Boolean = try {
    scrollTo()
    check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    true
} catch (e: Throwable) {
    false
}

fun ViewInteraction.assertIsDisplayed() {
    assertTrue("View is not displayed", isDisplayed())
}

fun checkIfTextIsVisible(viewId: Int, expectedText: String) {
    onView(withId(viewId)).check(matches(withText(containsString(expectedText))))
}