package com.mytaxi.android_demo;

import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

class LoginView {

    private static final String userName="crazydog335";
    private static final String password="venture";

    //Matchers
    public static final Matcher userNameTextFieldMatcher = withId(R.id.edt_username);
    public static final Matcher passwordTextFieldMatcher = withId(R.id.edt_password);
    public static final Matcher submitButtonMatcher = withId(R.id.btn_login);

    //Text

    //Functions
    public SearchView login(String userName, String password){

        onView(userNameTextFieldMatcher).perform(clearText());
        onView(passwordTextFieldMatcher).perform(clearText());

        onView(userNameTextFieldMatcher).perform(typeText(userName));
        onView(passwordTextFieldMatcher).perform(typeText(password));
        onView(submitButtonMatcher).perform(click());
        return new SearchView();
    }

    public SearchView login(){
        return login(userName, password);
    }

}
