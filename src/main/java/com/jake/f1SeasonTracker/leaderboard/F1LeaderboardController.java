package com.jake.f1SeasonTracker.leaderboard;

import com.jake.f1SeasonTracker.driver.F1Driver;
import com.jake.f1SeasonTracker.driver.F1DriverRepository;
import com.jake.f1SeasonTracker.result.F1Result;
import com.jake.f1SeasonTracker.result.F1ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
public class F1LeaderboardController {

    @Autowired
    private F1DriverRepository f1DriverRepository;
    @Autowired
    private F1ResultRepository f1ResultRepository;

    @GetMapping("/getLeaderboard")
    public F1Leaderboard getLeaderboard() {
        F1Leaderboard leaderboard = new F1Leaderboard();
        ArrayList<F1Driver> drivers = getAllF1Drivers();
        for(F1Driver driver : drivers) {
            F1LeaderboardEntry entry = getF1LeaderboardEntry(driver);
            leaderboard.addF1LeaderboardEntry(entry);
        }
        return leaderboard;
    }

    @RequestMapping({"/standings"})
    public ModelAndView getStandings() {
        ModelAndView mav = new ModelAndView("standings");
        mav.addObject("standings", getLeaderboard().getF1LeaderboardEntries());
        return mav;
    }

    @GetMapping("/leaderboardEntry")
    public List<F1LeaderboardEntry> getLeaderboardEntry(@RequestParam String driverName) {
        F1Driver driver = getDriver(driverName);
        F1LeaderboardEntry entry = getF1LeaderboardEntry(driver);
        List<F1LeaderboardEntry> entryList = new ArrayList<F1LeaderboardEntry>();
        entryList.add(entry);
        return entryList;
    }

    public F1LeaderboardEntry getF1LeaderboardEntry(F1Driver driver) {
        F1LeaderboardEntry entry = new F1LeaderboardEntry(driver);
        entry.calculateResults(getResultsForF1Driver(driver));
        return entry;
    }

    ArrayList<F1Driver> getAllF1Drivers() {
        return (ArrayList<F1Driver>) f1DriverRepository.findAll();
    }
    F1Driver getDriver(String driverName) {
        return f1DriverRepository.findF1DriverByF1DriverName(driverName);
    }

    ArrayList<F1Result> getResultsForF1Driver(F1Driver f) {
        return (ArrayList<F1Result>) f1ResultRepository.findF1ResultsByF1Driver(f);
    }
}
