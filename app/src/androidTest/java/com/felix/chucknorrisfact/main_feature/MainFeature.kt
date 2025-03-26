package com.felix.chucknorrisfact.main_feature

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.felix.chucknorrisfact.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainFeature {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun `testChuckNorrisFactDisplayed`() {
        // Check if the Chuck Norris fact text is displayed
        onView(withText("Chuck Norris doesn't need to eat. He runs on the fear of his enemies."))
            .check { _, noViewFoundException ->
                if (noViewFoundException != null) {
                    throw noViewFoundException
                }
            }
    }

}