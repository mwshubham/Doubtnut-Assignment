package com.example.whatsnewinandroid.ui.activities

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.whatsnewinandroid.R
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

//    @Mock
//    lateinit var navController: NavController

    @Test
    fun launchSearchFragment_performClick_backPressToMainActivity() {
        val activity = ActivityScenario.launch(MainActivity::class.java)
        activity.onActivity {
//            Navigation.setViewNavController(, navController)
//            Mockito.verify(navController).navigate(navController.popBackStack())
        }
        Espresso.onView(ViewMatchers.withId(R.id.fab_search)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.rv_search_results)).check(matches(isDisplayed()))
//        Espresso.onView(ViewMatchers.withId(R.id.iv_back)).perform(ViewActions.click())
        pressBack()
        Espresso.onView(ViewMatchers.withId(R.id.bottom_nav_view)).check(matches(isDisplayed()))
    }

}