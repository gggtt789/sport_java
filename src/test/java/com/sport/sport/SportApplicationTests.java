package com.sport.sport;

import com.sport.sport.controllers.*;
import com.sport.sport.services.EventsServiceTests;
import com.sport.sport.services.MembersServiceTests;
import com.sport.sport.services.TeamsServiceTests;
import org.junit.Test;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;


public class SportApplicationTests {
    @Test
    public void useCasesTests() {
        JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        Result result = junit.run(
                EventsServiceTests.class,
                TeamsServiceTests.class,
                MembersServiceTests.class);
        assert (result.getFailureCount() == 0);
    }

    @Test
    public void integrationTests() {
        JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        Result result = junit.run(
                EventControllerTest.class,
                MembersControllerTest.class,
                MemberTeamsControllerTest.class,
                MainControllerTest.class,
                TeamsControllerTest.class);
        assert (result.getFailureCount() == 0);
    }
}