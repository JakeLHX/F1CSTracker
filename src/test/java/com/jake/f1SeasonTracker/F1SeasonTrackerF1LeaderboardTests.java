package com.jake.f1SeasonTracker;

import com.jake.f1SeasonTracker.leaderboard.F1Leaderboard;
import com.jake.f1SeasonTracker.leaderboard.F1LeaderboardEntry;
import com.jake.f1SeasonTracker.result.F1Result;
import com.jake.f1SeasonTracker.result.F1ResultRepository;
import com.jake.f1SeasonTracker.driver.F1Driver;
import com.jake.f1SeasonTracker.driver.F1DriverRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collection;

@SpringBootTest
public class F1SeasonTrackerF1LeaderboardTests {

    @Test
    void testCreateF1LeaderBoard() {
        F1Leaderboard newF1Leaderboard = new F1Leaderboard();
        Assert.notNull(newF1Leaderboard, "Leaderboard Must not be Null");
    }

    @Autowired
    private F1ResultRepository f1ResultRepository;
    @Autowired
    private F1DriverRepository f1DriverRepository;

    @Test
    void getAllResults() {
        Collection<F1Result> resultsArray = f1ResultRepository.findF1ResultsByF1DriverId(1);
        System.out.println(resultsArray);
    }

    @Test
    void getLeaderboardEntryForDriver1() {
        F1Driver driver1 = f1DriverRepository.findF1DriverById(1);
        F1LeaderboardEntry entry = getF1LeaderboardEntry(driver1);
        System.out.println(entry.getF1Driver() + " Scored " + entry.getTotal() +" Points");
    }

    @Test
    void getLeaderboardForAllDrivers() {
        F1Leaderboard leaderboard = new F1Leaderboard();
        ArrayList<F1Driver> drivers = getAllF1Drivers();
        for(F1Driver driver : drivers) {
            F1LeaderboardEntry entry = getF1LeaderboardEntry(driver);
            leaderboard.addF1LeaderboardEntry(entry);
        }
        leaderboard.tableFormat();
    }

    private F1LeaderboardEntry getF1LeaderboardEntry(F1Driver driver) {
        F1LeaderboardEntry entry = new F1LeaderboardEntry(driver);
        return entry;
    }

    ArrayList<F1Driver> getAllF1Drivers() {
        return (ArrayList<F1Driver>) f1DriverRepository.findAll();
    }

    ArrayList<F1Result> getResultsForF1Driver(F1Driver f) {
        return (ArrayList<F1Result>) f1ResultRepository.findF1ResultsByF1Driver(f);
    }

}
