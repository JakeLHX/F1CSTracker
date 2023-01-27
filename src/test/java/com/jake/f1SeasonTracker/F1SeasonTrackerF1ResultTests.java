package com.jake.f1SeasonTracker;

import com.jake.f1SeasonTracker.result.F1Result;
import com.jake.f1SeasonTracker.track.F1Track;
import com.jake.f1SeasonTracker.driver.F1Driver;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class F1SeasonTrackerF1ResultTests {

    F1Driver newF1Driver = new F1Driver("jake","password");
    F1Track newF1Track = new F1Track("Monza","Italy");

    @Test
    void testCreateFifthPlaceF1Result() {
        F1Result newF1Result = new F1Result(newF1Driver, newF1Track, 5);
        Assert.notNull(newF1Result, "Result Must not be Null");
        Assert.isTrue(newF1Result.getPoints() == 10, "Should have 10 points for this result");
    }

    @Test
    void testCreateFirstPlaceF1ResultwithFastestLap() {
        F1Result newF1Result = new F1Result(newF1Driver, newF1Track, 1, true);
        Assert.notNull(newF1Result, "Result Must not be Null");
        Assert.isTrue(newF1Result.getPoints() == 26, "Should have 26 points for this result");
    }

}
