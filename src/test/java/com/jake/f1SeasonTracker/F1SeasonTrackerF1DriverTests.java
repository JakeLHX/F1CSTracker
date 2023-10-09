package com.jake.f1SeasonTracker;

import com.jake.f1SeasonTracker.driver.F1Driver;
import com.jake.f1SeasonTracker.driver.F1DriverRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    void testCreateDriverWithProfilePic() throws IOException {
        String imagePath = "src/test/resources/test_driver_logo.png";
        byte[] driverLogoBytes = loadImageAsByteArray(imagePath);

        F1Driver newF1Driver = new F1Driver("jake", "Password", driverLogoBytes);
        Assert.notNull(newF1Driver, "User Must not be Null");
        Assert.isTrue(newF1Driver.getF1DriverName() == "jake", "Username should be Jake");
    }

    private static byte[] loadImageAsByteArray(String imagePath) throws IOException {
        Path path = Path.of(imagePath);
        return Files.readAllBytes(path);
    }

    @Test
    void testGetAllDrivers() {
        Collection<F1Driver> resultsArray = f1DriverRepository.findAll();
        System.out.println(resultsArray);
    }
}
