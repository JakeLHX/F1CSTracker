package com.jake.f1SeasonTracker.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class F1DriverController {

    @Autowired
    private F1DriverRepository f1DriverRepository;

    @PostMapping("/adddriver")
    public String addF1Driver(@RequestParam String username, @RequestParam String password) {
        F1Driver newF1Driver = new F1Driver(username, password);
        f1DriverRepository.save(newF1Driver);
        return "Added New User!";
    }

    @RequestMapping({"/listF1Drivers", "/"})
    public ModelAndView getAllDrivers() {
        ModelAndView mav = new ModelAndView("driverlist");
        mav.addObject("drivers", f1DriverRepository.findAll());
        return mav;
    }

    @GetMapping("/listdrivers")
    public Iterable<F1Driver> getF1Drivers() {
        return f1DriverRepository.findAll();
    }

}
