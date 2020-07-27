package com.ragdroid.dahaka.activity.login;

import android.app.Instrumentation;
import android.content.Intent;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.ragdroid.dahaka.DahakaTestApplication;
import com.ragdroid.dahaka.R;
import com.ragdroid.dahaka.app.TestComponent;
import com.ragdroid.dahaka.user.PokemonService;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.schedulers.TestScheduler;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;


/**
 * Created by garimajain on 28/08/17.
 */
@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Inject
    PokemonService service;

    @Inject
    TestScheduler testScheduler;

    @Rule
    public ActivityTestRule<LoginActivity> activityRule = new ActivityTestRule<>(
            LoginActivity.class, true);

    @Before
    public void setUp() throws Exception {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        DahakaTestApplication app
                = (DahakaTestApplication) instrumentation.getTargetContext().getApplicationContext();
        TestComponent component = (TestComponent) app.getTestComponent();
        component.inject(this);
    }

    @Test
    public void testOne() throws InterruptedException {
        when(service.getPokemon("pikachu")).thenReturn(Single.just(TestDataFactory.getDummyPokemon()));
        LoginActivity loginActivity = activityRule.launchActivity(new Intent());
        onView(withId(R.id.login_input)).perform(typeText("pikachu"), closeSoftKeyboard());

        onView(withId(R.id.login_submit_button))
                .perform(click());
        testScheduler.triggerActions();
        onView(withText("Login Successful"))
                .inRoot(withDecorView(not(is(loginActivity.getWindow().getDecorView()))))
                .check(matches(isDisplayed()));


    }

}