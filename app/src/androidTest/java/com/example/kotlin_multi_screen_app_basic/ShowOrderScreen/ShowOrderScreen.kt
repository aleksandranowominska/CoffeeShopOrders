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
        val expectedText = "I ordered\n1. $order1\n2. $order2\n3. $order3\n4. $order4"
        checkIfTextIsVisible(viewId, expectedText)
    }
}