package com.mytaxi.android_demo;

import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

class DriverInfoView {

    //Matchers
    public static final Matcher driverAvatarMatcher = withId(R.id.imageViewDriverAvatar);
    public static final Matcher driverNameMatcher = withId(R.id.textViewDriverName);
    public static final Matcher driverLocationMatcher = withId(R.id.imageViewDriverLocation);
    public static final Matcher driverDateMatcher = withId(R.id.textViewDriverDate);
    public static final Matcher callBtnMatcher = withId(R.id.fab);

    //Text
    public static final String driverLocation = "6834 charles st";
    public static final String driverDate = "2002-10-18";

    //Functions
    public void callDriver(){
        onView(callBtnMatcher).perform(click());
    }

}
