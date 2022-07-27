package com.example.cardiacrecorder;

import android.app.Activity;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.MatcherAssert.assertThat;

import static java.util.EnumSet.allOf;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.Map;

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class UItest {

    @Rule
    public ActivityScenarioRule<MainActivity> activity_rule = new ActivityScenarioRule<>(MainActivity.class);

    /**
     * Splash Screen UI Test
     */

    @Test
    public void asplash_test(){
        onView(withId(R.id.splashScreen)).check(matches(isDisplayed()));
        onView(withText("Cardiac Recorder")).check(matches(isDisplayed()));
        onView(withId(R.id.splashScreenImg)).check(matches(isDisplayed()));
        onView(withText("Created by Nafiul and Iftee")).check(matches(isDisplayed()));
    }

    /**
     * Recycle View UI Test
     */

    @Test
    public void blistTest(){
        onView(withId(R.id.homelist)).check(matches(isDisplayed()));
        onView(withId(R.id.recyclerListId)).check(matches(isDisplayed()));
        onView(withId(R.id.bottom)).check(matches(isDisplayed()));
        onView(withId(R.id.addDataId)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.recyclerListId))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.detailedview)).check(matches(isDisplayed()));
    }

    /**
     * Add data UI Test
     */

    @Test
    public void cadddata_test(){
        onView(withId(R.id.recyclerListId)).check(matches(isDisplayed()));
        onView(withId(R.id.addDataId)).check(matches(isDisplayed()));
        onView(withId(R.id.addDataId)).perform(click());
        onView(withId(R.id.adddatalayout)).check(matches(isDisplayed()));
        onView(withText("Add Information")).check(matches(isDisplayed()));
        onView(withId(R.id.heartRateAddId)).check(matches(isDisplayed()));
        onView(withId(R.id.heartRateAddId)).perform(typeText("72"));
        onView(withId(R.id.systolicPressureAddId)).check(matches(isDisplayed()));
        onView(withId(R.id.systolicPressureAddId)).perform(typeText("120"));
        onView(withId(R.id.diastolicPressureAddId)).check(matches(isDisplayed()));
        onView(withId(R.id.diastolicPressureAddId)).perform(typeText("80"));
        onView(withId(R.id.commentAddId)).check(matches(isDisplayed()));
        onView(withId(R.id.commentAddId)).perform(typeText("Resting"));
        Espresso.pressBack();
        onView(withId(R.id.addButtonId)).check(matches(isDisplayed()));
        onView(withId(R.id.addButtonId)).perform(click());
        onView(withId(R.id.recyclerListId)).check(matches(isDisplayed()));
    }




}
