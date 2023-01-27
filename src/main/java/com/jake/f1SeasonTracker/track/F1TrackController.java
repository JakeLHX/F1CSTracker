package com.jake.f1SeasonTracker.track;

import com.jake.f1SeasonTracker.country.Country;
import com.jake.f1SeasonTracker.country.CountryRepository;
import com.jake.f1SeasonTracker.result.F1ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
public class F1TrackController {
    @Autowired
    private F1TrackRepository f1TrackRepository;

    @Autowired
    private F1ResultRepository f1ResultRepository;
    @Autowired
    private CountryRepository countryRepository;

    @PostMapping("/addtrack")
    public String addTrack(@RequestParam String name, @RequestParam String country) throws IOException {
        Country newCountry = new Country();
        newCountry = countryRepository.findCountryByName(country);
        if(newCountry == null) {
            newCountry = makeNewCountryIfNull(country, countryRepository);
        }
        F1Track newF1Track = new F1Track();
        newF1Track = f1TrackRepository.findF1TrackByName(name);
        if(newF1Track == null) {
            return makeNewF1TrackIfNull(name, newCountry, f1TrackRepository);
        }
        if(newF1Track.getCountry() != newCountry){
            newF1Track.setCountry(newCountry);
            f1TrackRepository.save(newF1Track);
        }
        return "Track already exists with ID "+ newF1Track.getId();
    }

    private static Country makeNewCountryIfNull(String country, CountryRepository countryRepository) {
        Country newCountry;
        newCountry = new Country(country);
        countryRepository.save(newCountry);
        System.out.println("Making new Country:"+ country);
        return newCountry;
    }

    private static String makeNewF1TrackIfNull(String name, Country newCountry, F1TrackRepository f1TrackRepository) {
        F1Track newF1Track;
        newF1Track = new F1Track(name, newCountry);
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

    @RequestMapping({"/track/{itemid}"})
    public ModelAndView showTrack(@PathVariable("itemid") String itemid) {
        ModelAndView mav = new ModelAndView("showTrack");
        mav.addObject("track", f1TrackRepository.findTrackById(Integer.valueOf(itemid)));
        mav.addObject("result", f1ResultRepository.findResultsByTrackId(Integer.valueOf(itemid)));
        return mav;
    }
}
