package com.jake.f1SeasonTracker.driver;

import com.jake.f1SeasonTracker.result.F1ResultRepository;
import com.jake.f1SeasonTracker.track.F1Track;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@Transactional
public class F1DriverController {

    @Autowired
    private F1DriverRepository f1DriverRepository;

    @Autowired
    private F1ResultRepository f1ResultRepository;

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

    @RequestMapping({"/showF1Driver/{f1DriverId}"})
    public ModelAndView showF1Driver(@PathVariable("f1DriverId") Integer f1DriverId) {
        ModelAndView mav = new ModelAndView("showDriver");
        mav.addObject("f1Driver", f1DriverRepository.findF1DriverById(f1DriverId));
        return mav;
    }

    @GetMapping("/showF1Driver/image/{id}")
    public void showDriverImage(@PathVariable Integer id,
                               HttpServletResponse response) throws IOException {
        response.setContentType("image/*");

        F1Driver driver = f1DriverRepository.findF1DriverById(id);

        InputStream is = new ByteArrayInputStream(driver.getF1DriverLogo());
        IOUtils.copy(is, response.getOutputStream());
    }

    @RequestMapping({"/newDriver"})
    public ModelAndView newDriver() {
        F1Driver newF1Driver = new F1Driver();
        ModelAndView mav = new ModelAndView("newDriver");
        mav.addObject("driver", newF1Driver);
        return mav;
    }

    @PostMapping("/newDriver")
    public ModelAndView submitForm(@ModelAttribute("driver") F1Driver newF1Driver,
                                   @RequestParam(value = "imagePicker", required = false) MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            // Convert the image file to a byte array and set it in the F1Driver object
            newF1Driver.setF1DriverLogo(imageFile.getBytes());
        }
        f1DriverRepository.save(newF1Driver);
        System.out.println(newF1Driver);
        return getAllDrivers();
    }

    @PostMapping("/deleteF1Driver/{id}")
    public Map<String, Boolean> deleteF1DriverByID(@PathVariable Integer id) {
        f1ResultRepository.deleteF1ResultsByF1DriverId(id);
        f1DriverRepository.deleteF1DriverById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @GetMapping("/listdrivers")
    public Iterable<F1Driver> getF1Drivers() {
        return f1DriverRepository.findAll();
    }

}
