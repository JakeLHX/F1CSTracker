package com.jake.f1SeasonTracker;

import com.jake.f1SeasonTracker.track.F1Track;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class F1SeasonTrackerF1TrackTests {

    @Test
    void testCreateF1Track() {
        F1Track newF1Track = new F1Track("Monza","Italy");
        Assert.notNull(newF1Track, "Track Must not be Null");
        Assert.isTrue(newF1Track.getName() == "Monza", "Name should be Monza");
        System.out.println(newF1Track.getCountryFlag());
    }

}
