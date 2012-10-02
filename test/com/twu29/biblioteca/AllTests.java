package com.twu29.biblioteca;

import junit.framework.JUnit4TestAdapter;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({TestUser.class, TestMovie.class, TestLibrary.class, TestItem.class})
public class AllTests {
    public static void main(String[] args) {
        junit.textui.TestRunner.run(mySuite());
    }

    public static JUnit4TestAdapter mySuite() {
        return new JUnit4TestAdapter(AllTests.class);
    }
}
