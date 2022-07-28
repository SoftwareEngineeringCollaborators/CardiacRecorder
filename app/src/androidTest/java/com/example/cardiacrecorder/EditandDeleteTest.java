package com.example.cardiacrecorder;

import android.app.Activity;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.longClick;
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
/**
 * UI Testing of edit and delete data
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class EditandDeleteTest {

    @Rule
    public ActivityScenarioRule<Home> activity_rule = new ActivityScenarioRule<>(Home.class);

    /**
     * Edit Data UI Test
     */

    @Test
    public void deditdata_test(){
        onView(withId(R.id.recyclerListId)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.recyclerListId))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.detailedview)).check(matches(isDisplayed()));
        onView(withId(R.id.editButtonId)).check(matches(isDisplayed()));
        onView(withId(R.id.editButtonId)).perform(click());
        onView(withText("Update Information")).check(matches(isDisplayed()));
        onView(withId(R.id.heartRateAddId)).check(matches(isDisplayed()));
        onView(withId(R.id.heartRateAddId)).perform(clearText());
        onView(withId(R.id.heartRateAddId)).perform(typeText("80"));
        onView(withId(R.id.systolicPressureAddId)).check(matches(isDisplayed()));
        onView(withId(R.id.systolicPressureAddId)).perform(clearText());
        onView(withId(R.id.systolicPressureAddId)).perform(replaceText("140"));
        onView(withId(R.id.diastolicPressureAddId)).check(matches(isDisplayed()));
        onView(withId(R.id.diastolicPressureAddId)).perform(clearText());
        onView(withId(R.id.diastolicPressureAddId)).perform(typeText("60"));
        onView(withId(R.id.commentAddId)).check(matches(isDisplayed()));
        onView(withId(R.id.commentAddId)).perform(clearText());
        onView(withId(R.id.commentAddId)).perform(typeText("Working"));
        Espresso.pressBack();
        onView(withId(R.id.addButtonId)).check(matches(isDisplayed()));
        onView(withId(R.id.addButtonId)).perform(click());
        onView(withId(R.id.recyclerListId)).check(matches(isDisplayed()));
    }

    /**
     * Delete Data UI Test
     */

    @Test
    public void eDelete(){
        onView(withId(R.id.homelist)).check(matches(isDisplayed()));
        onView(withId(R.id.recyclerListId)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.recyclerListId))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, longClick()));
        onView(withText("YES")).perform(click());
    }


}
