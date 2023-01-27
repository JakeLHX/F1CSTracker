package com.jake.f1SeasonTracker.driver;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface F1DriverRepository extends CrudRepository<F1Driver, Integer> {

    F1Driver findF1DriverById(Integer id);

    F1Driver deleteF1DriverById(Integer id);

    F1Driver findF1DriverByF1DriverName(String driverName);

    Collection<F1Driver> findAll();
}
