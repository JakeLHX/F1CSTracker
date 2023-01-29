package com.jake.f1SeasonTracker.result;

import com.jake.f1SeasonTracker.driver.F1Driver;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface F1ResultRepository extends CrudRepository<F1Result, Integer>  {

    F1Result findResultById(Integer id);

    Integer deleteResultById(Integer id);

    Integer deleteF1ResultsByTrackId(Integer id);

    Collection<F1Result> findF1ResultsByF1Driver(F1Driver driver);

    Collection<F1Result> findF1ResultsByF1DriverId(int i);

    Collection<F1Result> findAll();
    Collection<F1Result> findResultsByTrackId(Integer itemid);
}
