package com.example.kotlin_multi_screen_app_basic.ShowOrderScreen

import android.util.Log
import android.view.autofill.AutofillManager
import com.example.kotlin_multi_screen_app_basic.R
import com.example.kotlin_multi_screen_app_basic.Utils.checkIfTextIsVisible
import com.example.kotlin_multi_screen_app_basic.Utils.findViewById
import com.example.kotlin_multi_screen_app_basic.Utils.testContext
import com.example.kotlin_multi_screen_app_basic.Utils.waitUntilAllDisplayed

class ShowOrderScreen {
    private val orderListTextView = findViewById(R.id.textView)

    init {
        val autofillManager: AutofillManager = testContext.getSystemService(AutofillManager::class.java)
        autofillManager.disableAutofillServices()
        waitUntilAllDisplayed(
            orderListTextView
        )
    }

    fun getOrderListTextViewId(): Int {
        return R.id.textView
    }

    fun assertOrderedItems(viewId: Int, order1: String, order2: String, order3: String, order4: String) {
        val orderTexts = listOf("I ordered", order1, order2, order3, order4)

        for (text in orderTexts) {
            Log.d("blabla", "sprawdzam tekst: $text na ekranie")
            checkIfTextIsVisible(viewId, text)
        }
    }
}