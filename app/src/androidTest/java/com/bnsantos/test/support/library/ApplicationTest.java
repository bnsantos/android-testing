package com.bnsantos.test.support.library;

import android.test.ApplicationTestCase;

import org.junit.Test;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<App> {
    public ApplicationTest() {
        super(App.class);
    }

    @Test
    public void test(){
        assertEquals(true, true);
    }
}