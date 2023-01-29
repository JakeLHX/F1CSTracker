package com.jake.f1SeasonTracker.track;

import com.jake.f1SeasonTracker.driver.F1Driver;
import com.jake.f1SeasonTracker.driver.F1DriverRepository;
import com.jake.f1SeasonTracker.result.F1Result;
import com.jake.f1SeasonTracker.result.F1ResultRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@Transactional
public class F1TrackController {
    @Autowired
    private F1TrackRepository f1TrackRepository;

    @Autowired
    private F1DriverRepository f1DriverRepository;

    @Autowired
    private F1ResultRepository f1ResultRepository;

    @PostMapping("/addtrack")
    public String addTrack(@RequestParam String name, @RequestParam String country) throws IOException {
        F1Track newTrack = f1TrackRepository.findF1TrackByName(name);
        if(newTrack.getName().isEmpty()) {
            newTrack = new F1Track(name, country);
        }
        f1TrackRepository.save(newTrack);
        return "Track already exists with ID "+ newTrack.getId();
    }

    private static String makeNewF1TrackIfNull(String name, String country, F1TrackRepository f1TrackRepository) {
        F1Track newF1Track;
        newF1Track = new F1Track(name, country);
        f1TrackRepository.save(newF1Track);
        return "Making new F1 Track:" + name;
    }

    @GetMapping("/listtracks")
    public Iterable<F1Track> getTracks() {
        return f1TrackRepository.findAll();
    }

    @RequestMapping({"/listF1Tracks"})
    public ModelAndView getAllTracks() {
        ModelAndView mav = new ModelAndView("tracklist");
        mav.addObject("tracks", f1TrackRepository.findAll());
        return mav;
    }

    @RequestMapping({"/newTrack"})
    public ModelAndView newTrack() {
        F1Track newF1Track = new F1Track();
        ModelAndView mav = new ModelAndView("newTrack");
        mav.addObject("track", newF1Track);
        return mav;
    }

    @PostMapping("/deleteTrack/{id}")
    public Map<String, Boolean> deleteTrackByID(@PathVariable Integer id) {
        f1ResultRepository.deleteF1ResultsByTrackId(id);
        f1TrackRepository.deleteTrackById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PostMapping("/newTrack")
    public ModelAndView submitForm(@ModelAttribute("track") F1Track newF1Track) {
        f1TrackRepository.save(newF1Track);
        System.out.println(newF1Track);
        return getAllTracks();
    }

    @RequestMapping({"/track/{itemid}"})
    public ModelAndView showTrack(@PathVariable("itemid") String itemid) {
        ModelAndView mav = new ModelAndView("showTrack");
        mav.addObject("track", f1TrackRepository.findTrackById(Integer.valueOf(itemid)));
        Collection<F1Result> trackResult = f1ResultRepository.findResultsByTrackId(Integer.valueOf(itemid));
        if(!(trackResult.size() == 0)) {
            mav.addObject("result", trackResult);

        } else {
            Collection<F1Driver> drivers = f1DriverRepository.findAll();
            mav.addObject("drivers", drivers);
        }
        return mav;
    }
}
