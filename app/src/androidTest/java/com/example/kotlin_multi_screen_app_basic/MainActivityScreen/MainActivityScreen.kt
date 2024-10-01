package com.example.kotlin_multi_screen_app_basic.MainActivityScreen

import android.util.Log
import android.view.autofill.AutofillManager
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.typeText
import com.example.kotlin_multi_screen_app_basic.R
import com.example.kotlin_multi_screen_app_basic.Utils.TestingConsts.TESTING_TAG
import com.example.kotlin_multi_screen_app_basic.Utils.click
import com.example.kotlin_multi_screen_app_basic.Utils.findViewById
import com.example.kotlin_multi_screen_app_basic.Utils.testContext
import com.example.kotlin_multi_screen_app_basic.Utils.waitUntilAllDisplayed

class MainActivityScreen {

    private val titleLabel = findViewById(R.id.mainScreenTitle)
    private val order1EditText = findViewById(R.id.eTT1)
    private val order2EditText = findViewById(R.id.eTT2)
    private val order3EditText = findViewById(R.id.eTT3)
    private val order4EditText = findViewById(R.id.eTT4)
    private val orderButton = findViewById(R.id.buttonOrder)

    init {
        Log.d(TESTING_TAG, "Opening Main Activity Screen")
        val autofillManager: AutofillManager =
            testContext.getSystemService(AutofillManager::class.java)
        autofillManager.disableAutofillServices()
        waitUntilAllDisplayed(
            titleLabel,
            order1EditText,
            order2EditText,
            order3EditText,
            order4EditText,
            orderButton
        )
    }

    fun typeProductToOrder(productToOrder: String, orderIndex: Int) {
        Log.d(TESTING_TAG,"Typing \"$productToOrder\" to edit text")
        val selectedEditText = when (orderIndex) {
            1 -> order1EditText
            2 -> order2EditText
            3 -> order3EditText
            4 -> order4EditText
            else -> throw IllegalArgumentException("Invalid order index")
        }
        selectedEditText.perform(clearText(), typeText(productToOrder))
    }

    fun confirmOrder() {
        Log.d(TESTING_TAG, "Confirming the order")
        orderButton.click()
    }

    companion object {
        const val COFFEE_ESPRESSO = "Espresso"
        const val COFFEE_V60 = "V60"
        const val COFFEE_LATTE = "Latte"
        const val COFFEE_CAPPUCCINO = "Cappuccino"
        const val COFFEE_CHEMEX = "Chemex"
        const val SANDWICH_CHEESE = "Sandwich with cheese"
        const val SANDWICH_HAM = "Sandwich with ham"
    }
}