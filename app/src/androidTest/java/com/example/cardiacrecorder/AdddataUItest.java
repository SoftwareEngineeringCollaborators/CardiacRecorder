package com.example.cardiacrecorder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.action.ViewActions.click;

import android.view.View;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

public class AdddataUItest {
    @Rule
    public ActivityScenarioRule<addDataActivity> activity_rule = new ActivityScenarioRule<>(addDataActivity.class);

    /**
     * Adding data
     */
    @Test
    public void adddata_test(){
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
    }
}
