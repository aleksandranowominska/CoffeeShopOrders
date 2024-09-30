package com.example.kotlin_multi_screen_app_basic

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.kotlin_multi_screen_app_basic.MainActivityScreen.MainActivityScreen
import com.example.kotlin_multi_screen_app_basic.MainActivityScreen.MainActivityScreen.Companion.COFFEE_ESPRESSO
import com.example.kotlin_multi_screen_app_basic.MainActivityScreen.MainActivityScreen.Companion.COFFEE_V60
import com.example.kotlin_multi_screen_app_basic.MainActivityScreen.MainActivityScreen.Companion.SANDWICH_CHEESE
import com.example.kotlin_multi_screen_app_basic.MainActivityScreen.MainActivityScreen.Companion.SANDWICH_HAM
import com.example.kotlin_multi_screen_app_basic.ShowOrderScreen.ShowOrderScreen
import com.example.kotlin_multi_screen_app_basic.Utils.MyIdlingResource
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private var scenario: ActivityScenario<MainActivity>? = null
    private lateinit var idlingResource: MyIdlingResource

    @Before
    fun setUp() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
        // Inicjalizacja IdlingResource
        idlingResource = MyIdlingResource()
        IdlingRegistry.getInstance().register(idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(idlingResource)
        scenario?.moveToState(Lifecycle.State.DESTROYED)
        scenario?.close()
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.kotlin_multi_screen_app_basic", appContext.packageName)
    }

    @Test
    fun placeOrder() {
        var mainActivityScreen = MainActivityScreen()
        mainActivityScreen.typeProductToOrder(COFFEE_ESPRESSO,1)
        mainActivityScreen.typeProductToOrder(SANDWICH_CHEESE,2)
        mainActivityScreen.typeProductToOrder(COFFEE_V60,3)
        mainActivityScreen.typeProductToOrder(SANDWICH_HAM, 4)
        idlingResource.setIdleState(false)
        mainActivityScreen.confirmOrder()
        idlingResource.setIdleState(true)
        var showOrderScreen = ShowOrderScreen()
        showOrderScreen.assertOrderedItems(showOrderScreen.getOrderListTextViewId(), COFFEE_ESPRESSO, SANDWICH_CHEESE, COFFEE_V60, SANDWICH_HAM)
}