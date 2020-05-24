package com.example.whatsnewinandroid.ui.fragments.search

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.whatsnewinandroid.R
import com.example.whatsnewinandroid.data.repository.DoubtnutRepository
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@MediumTest
@RunWith(AndroidJUnit4::class)
class SearchFragmentTest {
    @get:Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule()

//    @Mock
//    lateinit var doubtnutRemoteDataSource: DoubtnutRemoteDataSource

    @Mock
    lateinit var doubtnutRepository: DoubtnutRepository

    fun setup() {
        println("setup()")
    }

    @Test
    fun launchFragmentInContainer_displayedInUi() {
        // Supplying the theme is necessary because fragments usually get
        // their theme from their parent activity.
        runBlocking {
            TestSearchFragment.testViewModel = SearchFragmentVM(doubtnutRepository)
        }
        launchFragmentInContainer<TestSearchFragment>(null, R.style.Theme_Main_DayNight)

        onView(withId(R.id.iv_back)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_search)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_search_results)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_search)).check(matches(withHint(R.string.search)))
    }

    @Test
    fun launchFragmentInContainer_notDisplayedInUi() {
        // Supplying the theme is necessary because fragments usually get
        // their theme from their parent activity.
        runBlocking {
            TestSearchFragment.testViewModel = SearchFragmentVM(doubtnutRepository)
        }
        launchFragmentInContainer<TestSearchFragment>(null, R.style.Theme_Main_DayNight)
        onView(withId(R.id.iv_clear_input)).check(matches(not(isDisplayed())))
        onView(withId(R.id.progress_bar_search)).check(matches(not(isDisplayed())))
    }

    @Test
    fun launchFragmentInContainer_doesNotExist() {
        // Supplying the theme is necessary because fragments usually get
        // their theme from their parent activity.
        runBlocking {
            TestSearchFragment.testViewModel = SearchFragmentVM(doubtnutRepository)
        }
        launchFragmentInContainer<TestSearchFragment>(null, R.style.Theme_Main_DayNight)
        onView(withId(R.id.tv_bookmark)).check(doesNotExist())
    }
}