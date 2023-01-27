package com.jake.f1SeasonTracker;

import com.jake.f1SeasonTracker.driver.F1Driver;
import com.jake.f1SeasonTracker.driver.F1DriverRepository;
import com.jake.f1SeasonTracker.leaderboard.F1Leaderboard;
import com.jake.f1SeasonTracker.leaderboard.F1LeaderboardEntry;
import com.jake.f1SeasonTracker.result.F1Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
public class F1Controller {

    @Autowired
    private F1DriverRepository f1DriverRepository;

    @GetMapping("/finddriver/{id}")
    public F1Driver findF1DriverById(@PathVariable Integer id) {
        return f1DriverRepository.findF1DriverById(id);
    }

}
