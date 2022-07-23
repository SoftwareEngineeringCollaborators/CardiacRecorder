package com.example.cardiacrecorder;

import android.app.Activity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.MatcherAssert.assertThat;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest

public class SplashUItest{

    @Rule
    public ActivityScenarioRule<MainActivity> activity_rule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void splash_test(){
        onView(withId(R.id.splashScreen)).check(matches(isDisplayed()));
        onView(withText("Cardiac Recorder")).check(matches(isDisplayed()));
        onView(withId(R.id.splashScreenImg)).check(matches(isDisplayed()));
        onView(withText("Created by Nafiul and Iftee")).check(matches(isDisplayed()));
    }
}
