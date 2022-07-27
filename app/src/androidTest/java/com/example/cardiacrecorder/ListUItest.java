package com.example.cardiacrecorder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.action.ViewActions.click;

import android.view.View;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

public class ListUItest {

    @Rule
    public ActivityScenarioRule<Home> activity_rule = new ActivityScenarioRule<>(Home.class);

    @Test
    public void List_test(){
        onView(withId(R.id.homelist)).check(matches(isDisplayed()));
        onView(withId(R.id.recyclerListId)).check(matches(isDisplayed()));
        onView(withId(R.id.bottom)).check(matches(isDisplayed()));
        onView(withId(R.id.addDataId)).check(matches(isDisplayed()));
        onView(withId(R.id.homelist)).perform(click());
    }
}
