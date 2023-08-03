package com.jake.f1SeasonTracker.driver;

import com.jake.f1SeasonTracker.track.F1Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class F1DriverController {

    @Autowired
    private F1DriverRepository f1DriverRepository;

    @PostMapping("/adddriver")
    public String addF1Driver(@RequestParam String username, @RequestParam String password, @RequestParam(required = false) byte[] image) {
        F1Driver newF1Driver;

        if (image != null) {
            newF1Driver = new F1Driver(username, password, image);
        } else {
            newF1Driver = new F1Driver(username, password);
        }

        f1DriverRepository.save(newF1Driver);
        return "Added New User!";
    }

    @RequestMapping({"/listF1Drivers"})
    public ModelAndView getAllDrivers() {
        ModelAndView mav = new ModelAndView("driverlist");
        mav.addObject("drivers", f1DriverRepository.findAll());
        return mav;
    }

    @RequestMapping({"/newDriver"})
    public ModelAndView newDriver() {
        F1Driver newF1Driver = new F1Driver();
        ModelAndView mav = new ModelAndView("newDriver");
        mav.addObject("driver", newF1Driver);
        return mav;
    }

    @PostMapping("/newDriver")
    public ModelAndView submitForm(@ModelAttribute("driver") F1Driver newF1Driver) {
        f1DriverRepository.save(newF1Driver);
        System.out.println(newF1Driver);
        return getAllDrivers();
    }

    @GetMapping("/listdrivers")
    public Iterable<F1Driver> getF1Drivers() {
        return f1DriverRepository.findAll();
    }

}
