package com.bnsantos.test.support.library;

import android.support.design.internal.NavigationMenu;
import android.support.design.internal.NavigationMenuItemView;
import android.support.design.widget.NavigationView;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.bnsantos.test.support.library.network.MovieService;
import com.bnsantos.test.support.library.network.model.MovieResponse;
import com.bnsantos.test.support.library.view.MainActivity;
import com.example.android.testing.notes.custom.action.NavigationViewActions;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import retrofit.mock.MockRetrofit;
import retrofit.mock.NetworkBehavior;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.instanceOf;

/**
 * Created by bruno on 18/01/16.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity>{
    private static final String API_KEY = "TEST_API_KEY";
    private MovieService service;
    public MainActivityTest() {
        super(MainActivity.class);
        App app = (App) InstrumentationRegistry.getTargetContext().getApplicationContext();
        App.API_KEY = API_KEY;


        MockRetrofit mockRetrofit = new MockRetrofit(NetworkBehavior.create(), new NetworkBehavior.Adapter<MovieResponse>() {
            @Override
            public MovieResponse applyBehavior(NetworkBehavior networkBehavior, MovieResponse movieResponse) {
                return movieResponse;
            }
        });
        service = mockRetrofit.create(MovieService.class, null);

        app.setMovieService(service);
    }

    @Test
    public void test(){
        getActivity();
        assertEquals(true, true);
    }

    @Before
    public void setup() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
    }
}
