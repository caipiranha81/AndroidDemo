package com.mytaxi.android_demo;

import java.util.Arrays;
import java.util.List;

import android.support.test.espresso.DataInteraction;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;

import org.hamcrest.Matcher;
import static org.hamcrest.Matchers.is;


class SearchView {

    //Matchers
    public static final Matcher searchFieldMatcher = withId(R.id.textSearch);
    public static final Matcher searchResultPopupMatcher = withClassName(is("android.widget.PopupWindow$PopupBackgroundView"));

    //Text
    private static final List<String> drivers= Arrays.asList(
            "Sara Christensen",
            "Sara Scott",
            "Saana Kemppainen",
            "Sara Roman"
    );

    public static final String searchFieldText = "Search driver here";

    //Functions
    public void searchForDriver(String keyword){
        onView(searchFieldMatcher).perform(typeText(keyword));
    }

    public DriverInfoView chooseDriver(String driverName){
        onData(withText(driverName)).perform(click());
        return new DriverInfoView();
    }

    public String getDriver(int i){
        return drivers.get(i);
    }

    public DataInteraction getDriverFromPopup(int i){
        return onData(searchResultPopupMatcher).atPosition(i);
    }

}
