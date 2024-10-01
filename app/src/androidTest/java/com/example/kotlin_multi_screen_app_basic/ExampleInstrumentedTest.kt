package com.example.kotlin_multi_screen_app_basic

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.kotlin_multi_screen_app_basic.MainActivityScreen.MainActivityScreen
import com.example.kotlin_multi_screen_app_basic.MainActivityScreen.MainActivityScreen.Companion.COFFEE_ESPRESSO
import com.example.kotlin_multi_screen_app_basic.MainActivityScreen.MainActivityScreen.Companion.COFFEE_V60
import com.example.kotlin_multi_screen_app_basic.MainActivityScreen.MainActivityScreen.Companion.SANDWICH_CHEESE
import com.example.kotlin_multi_screen_app_basic.MainActivityScreen.MainActivityScreen.Companion.SANDWICH_HAM
import com.example.kotlin_multi_screen_app_basic.ShowOrderScreen.ShowOrderScreen
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private var scenario: ActivityScenario<MainActivity>? = null

    @Before
    fun setUp() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.kotlin_multi_screen_app_basic", appContext.packageName)
    }

    @Test
    fun placeOrder() {
        var mainActivityScreen = MainActivityScreen()

        mainActivityScreen.typeProductToOrder(COFFEE_ESPRESSO, 1)
        mainActivityScreen.typeProductToOrder(SANDWICH_CHEESE, 2)
        mainActivityScreen.typeProductToOrder(COFFEE_V60, 3)
        mainActivityScreen.typeProductToOrder(SANDWICH_HAM, 4)

        mainActivityScreen.confirmOrder()

        val showOrderScenario = ActivityScenario.launch(ShowOrderActivity::class.java)

        var showOrderScreen = ShowOrderScreen()
        showOrderScreen.assertOrderedItems(
            showOrderScreen.getOrderListTextViewId(),
            COFFEE_ESPRESSO,
            SANDWICH_CHEESE,
            COFFEE_V60,
            SANDWICH_HAM
        )
    }
}