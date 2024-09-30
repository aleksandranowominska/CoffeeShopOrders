package com.example.kotlin_multi_screen_app_basic.Utils

import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.actionWithAssertions

fun ViewInteraction.scrollTo() {
    try {
        perform(
            actionWithAssertions(NestedScrollToAction())
        )
    } catch (ignored: Exception) {
    }
}

fun ViewInteraction.click() {
    scrollTo()
    assertIsDisplayed()
    perform(ViewActions.click())
}