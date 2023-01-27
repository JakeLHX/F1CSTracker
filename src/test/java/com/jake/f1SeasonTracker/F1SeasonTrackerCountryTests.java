package com.jake.f1SeasonTracker;

import com.jake.f1SeasonTracker.country.Country;
import com.jake.f1SeasonTracker.country.CountryRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Collection;

@SpringBootTest
public class F1SeasonTrackerCountryTests {

    @Autowired
    private CountryRepository countryRepository;

    @Test
    void testCreateCountry() {
        Country country = new Country("england");
        Assert.notNull(country, "User Must not be Null");
        Assert.isTrue(country.getName() == "england", "Name should be England");
    }

    @Test
    void testGetAllCountries() {
        Iterable<Country> resultsArray = countryRepository.findAll();
        System.out.println(resultsArray);
    }

}
