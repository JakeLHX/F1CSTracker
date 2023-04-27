package com.jake.f1SeasonTracker.result;

import com.jake.f1SeasonTracker.driver.F1Driver;
import com.jake.f1SeasonTracker.driver.F1DriverRepository;
import com.jake.f1SeasonTracker.track.F1Track;
import com.jake.f1SeasonTracker.track.F1TrackRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

@RestController
@Transactional
public class F1ResultController {

    @Autowired
    private F1TrackRepository f1TrackRepository;
    @Autowired
    private F1ResultRepository f1ResultRepository;
    @Autowired
    private F1DriverRepository f1DriverRepository;

    @RequestMapping("/addresult")
    public String addResult(@RequestParam Integer trackId,
                            @RequestParam Integer driverId,
                            @RequestParam Integer position,
                            @RequestParam(required = false) Boolean fastestlap,
                            @RequestParam(required = false) Integer comp ) {
        F1Track selectedTrack = f1TrackRepository.findTrackById(trackId);
        F1Driver selectedDriver = f1DriverRepository.findF1DriverById(driverId);
        F1Result newF1Result = new F1Result(selectedDriver, selectedTrack, position, fastestlap);
        if(!isNull(comp)) {
            newF1Result.setPoints(newF1Result.getPoints()+comp);
        }
        f1ResultRepository.save(newF1Result);
        return "Added New Result!";
    }

    @PostMapping("/clearTrackResults/{id}")
    public Map<String, Boolean> deleteResultsByTrackId(@PathVariable Integer id) {
        f1ResultRepository.deleteF1ResultsByTrackId(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @GetMapping("/listresults")
    public Iterable<F1Result> getResults() {
        return f1ResultRepository.findAll();
    }
}
