package com.jake.f1SeasonTracker.track;

import org.springframework.data.repository.CrudRepository;

public interface F1TrackRepository extends CrudRepository<F1Track, Integer> {

    F1Track findTrackById(Integer id);

    F1Track deleteTrackById(Integer id);

    F1Track findF1TrackByName(String name);

}
