package com.mytaxi.android_demo;

import android.support.test.runner.AndroidJUnit4;

import com.mytaxi.android_demo.activities.MainActivity;

import org.junit.Rule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class InstrumentedTests {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.ACCESS_FINE_LOCATION");

    @Before
    public void init() {
        mActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction();
    }

    @Test
    @LargeTest
    public void shouldSearchAndCallDriver() {

        waitForApp(5000);

        shouldNotLoginWithIncorrectData("TestUser", "Test");
        SearchView searchView = shouldLoginWithCorrectData();

        shouldSearchForDriver("sa");

        String driverName = searchView.getDriver(1);
        DriverInfoView driverInfoView = shouldChooseDriver(driverName);

        shouldCallDriver(driverName);

    }

    private void waitForApp(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private SearchView shouldLoginWithCorrectData() {
        //given
        LoginView loginView = new LoginView();
        onView(LoginView.userNameTextFieldMatcher).check(matches(isDisplayed()));
        onView(LoginView.passwordTextFieldMatcher).check(matches(isDisplayed()));
        onView(LoginView.submitButtonMatcher).check(matches(isDisplayed()));

        //when
        SearchView searchView = loginView.login();

        waitForApp(30000);

        //then
        onView(LoginView.userNameTextFieldMatcher).check(doesNotExist());

        return searchView;
    }


    private void shouldNotLoginWithIncorrectData(String userName, String password) {
        //given
        LoginView loginView = new LoginView();
        onView(LoginView.userNameTextFieldMatcher).check(matches(isDisplayed()));
        onView(LoginView.passwordTextFieldMatcher).check(matches(isDisplayed()));
        onView(LoginView.submitButtonMatcher).check(matches(isDisplayed()));

        //when
        SearchView searchView = loginView.login(userName, password);

        //then
        onView(LoginView.userNameTextFieldMatcher).check(matches(isDisplayed()));
        onView(LoginView.passwordTextFieldMatcher).check(matches(isDisplayed()));
        onView(LoginView.submitButtonMatcher).check(matches(isDisplayed()));
    }

    private void shouldSearchForDriver(String keyword) {
        //given
        SearchView searchView = new SearchView();
        onView(SearchView.searchFieldMatcher).check(matches(isDisplayed()));
        onView(SearchView.searchFieldMatcher).check(matches(withHint(SearchView.searchFieldText)));

        //when
        searchView.searchForDriver(keyword);

        //then
        onView(SearchView.searchFieldMatcher).check(matches(withText(keyword)));
        //onView(SearchView.searchResultPopupMatcher).check(matches(isDisplayed()));

        /*int i = 0;
        DataInteraction driver = null;
        do {
            driver = searchView.getDriverFromPopup(i);
            driver.check(matches(withText(searchView.getDriver(i))));
        } while (driver != null);*/
    }

    private DriverInfoView shouldChooseDriver(String driverName) {
        //given
        SearchView searchView = new SearchView();
        DriverInfoView driverInfoView;

        //when
        driverInfoView = searchView.chooseDriver(driverName);

        waitForApp(5000);

        //then
        onView(SearchView.searchResultPopupMatcher).check(doesNotExist());
        onView(SearchView.searchFieldMatcher).check(doesNotExist());

        return driverInfoView;
    }

    private void shouldCallDriver(String driverName) {
        //given
        DriverInfoView driverInfoView = new DriverInfoView();
        onView(DriverInfoView.driverNameMatcher).check(matches(withText(driverName)));
        onView(DriverInfoView.driverAvatarMatcher).check(matches(isDisplayed()));
        onView(DriverInfoView.driverDateMatcher).check(matches(withText(DriverInfoView.driverDate)));
        onView(DriverInfoView.driverLocationMatcher).check(matches(withText(DriverInfoView.driverLocation)));
        onView(DriverInfoView.callBtnMatcher).check(matches(isDisplayed()));

        //when
        driverInfoView.callDriver();

        waitForApp(5000);

        //then
        onView(DriverInfoView.driverAvatarMatcher).check(doesNotExist());
    }

}
