package com.example.newsapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.newsapp.activity.MainActivity
import com.example.newsapp.adapter.NewsAdapter
import com.example.newsapp.espresso.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)


    @Before
    fun registerResource(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun unregisterResource(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    // Test Recycler view is visible or not after api response.
    @Test
    fun isRecyclerVisible(){
        onView(withId(R.id.recyclerNews)).check(matches(isDisplayed()))
    }

    // Test NewsDetail Fragment is visible or not after recycler view item click.
    @Test
    fun isRecyclerNavigatingToDetailPage(){
        onView(withId(R.id.recyclerNews))
            .perform(RecyclerViewActions.actionOnItemAtPosition<NewsAdapter.NewsHolder>(1,click()))
        onView(withId(R.id.text_description)).check(matches(isDisplayed()))
    }

}