package com.example.kotlin_multi_screen_app_basic.Utils

import android.content.Context
import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry

val testContext: Context
    get() = InstrumentationRegistry.getInstrumentation().targetContext

fun ViewInteraction.getViewId(): String? {
    var viewId: String? = null
    perform(
        object : ViewAction {
            override fun getConstraints() = ViewMatchers.isAssignableFrom(View::class.java)

            override fun getDescription() = "Getting view's ID"

            override fun perform(uiController: UiController, view: View) {
                if (view.id == View.NO_ID) return
                try {
                    viewId = view.context.resources.getResourceEntryName(view.id)
                } catch (exception: Exception) {
                    // do nothing
                }
            }
        }
    )
    return viewId
}