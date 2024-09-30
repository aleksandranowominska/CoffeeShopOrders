package com.example.kotlin_multi_screen_app_basic.Utils

import android.view.View
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

fun withIndex(matcher: Matcher<View?>, index: Int): TypeSafeMatcher<View?> {
    return object : TypeSafeMatcher<View?>() {
        var currentIndex = 0
        var viewHashCode = 0

        override fun describeTo(description: Description) {
            description.appendText("with index: ")
            description.appendValue(index)
            matcher.describeTo(description)
        }

        public override fun matchesSafely(view: View?): Boolean {
            if (matcher.matches(view) && currentIndex++ == index) {
                viewHashCode = view.hashCode()
            }
            return view.hashCode() == viewHashCode
        }
    }
}