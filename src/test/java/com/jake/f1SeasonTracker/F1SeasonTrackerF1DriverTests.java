package com.jake.f1SeasonTracker;

import com.jake.f1SeasonTracker.driver.F1Driver;
import com.jake.f1SeasonTracker.driver.F1DriverRepository;
import com.jake.f1SeasonTracker.result.F1Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Collection;

@SpringBootTest
public class F1SeasonTrackerF1DriverTests {

    @Autowired
    private F1DriverRepository f1DriverRepository;

    @Test
    void testCreateDriver() {
        F1Driver newF1Driver = new F1Driver("jake","password");
        Assert.notNull(newF1Driver, "User Must not be Null");
        Assert.isTrue(newF1Driver.getF1DriverName() == "jake", "Username should be Jake");
    }

    @Test
    void testGetAllDrivers() {
        Collection<F1Driver> resultsArray = f1DriverRepository.findAll();
        System.out.println(resultsArray);
    }

}
