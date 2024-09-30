package com.example.kotlin_multi_screen_app_basic.Utils

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.kotlin_multi_screen_app_basic.Utils.Finders.Companion.LONG_TIMEOUT
import com.example.kotlin_multi_screen_app_basic.Utils.Finders.Companion.MILLISECONDS_100
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

// [Here](https://developer.android.com/training/testing/espresso/cheat-sheet) you can find Espresso cheat sheet

fun findViewById(@IdRes id: Int, index: Int = 0): ViewInteraction = onView(withIndex(withId(id), index))

fun waitUntilAllDisplayed(vararg interactions: ViewInteraction, timeout: Long = LONG_TIMEOUT) = waitUntil(
    condition = { interactions.all(ViewInteraction::isDisplayed) },
    message = { "Waiting for all views to be displayed timed out ($timeout ms): ${interactions.map { it.getViewId() }}" },
    timeout = timeout,
)

private fun waitUntil(condition: () -> Boolean, message: () -> String, timeout: Long) = runBlocking {
    try {
        withTimeout(timeout) {
            while (isActive) {
                if (condition()) break
                delay(MILLISECONDS_100)
            }
        }
    } catch (exception: TimeoutCancellationException) {
        throw Exception(message())
    }
}

class Finders {
    companion object {
        const val MILLISECONDS_100 = 100L
        const val LONG_TIMEOUT = 15_000L
    }
}