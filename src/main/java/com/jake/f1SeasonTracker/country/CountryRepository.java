package com.jake.f1SeasonTracker.country;

import com.jake.f1SeasonTracker.track.F1Track;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Integer> {

    Country findCountryById(Integer id);

    Country deleteCountryById(Integer id);

    Country findCountryByName(String name);

}
