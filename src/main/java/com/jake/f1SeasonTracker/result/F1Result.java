package com.jake.f1SeasonTracker.result;

import com.jake.f1SeasonTracker.track.F1Track;
import com.jake.f1SeasonTracker.driver.F1Driver;
import jakarta.persistence.*;

@Entity
public class F1Result {
    private Boolean fastestLap;
    @OneToOne
    @JoinColumn(name = "user_id")
    private F1Driver f1Driver;

    @OneToOne
    @JoinColumn(name = "track_id")
    private F1Track track;
    private Integer position;
    private Integer points;

    @Id
    @Column(name = "result_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public F1Result(F1Driver driver, F1Track track, Integer position) {
        this.f1Driver = driver;
        this.track = track;
        this.position = position;
        this.fastestLap = false;
        determinePoints();
    }

    public F1Result(F1Driver driver, F1Track track, Integer position, Boolean fastestLap) {
        this.f1Driver = driver;
        this.track = track;
        this.position = position;
        this.fastestLap = fastestLap;
        determinePoints();
    }

    private void determinePoints() {
        Integer[] positions = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        Integer[] pointsList = {25,18,15,12,10,8,6,4,2,1};
        boolean inBounds = (java.util.Arrays.asList(positions).contains(getPosition())) &&
                (java.util.Arrays.asList(positions).indexOf(getPosition()) < pointsList.length);
        if(inBounds) {
            Integer tempPoints = pointsList[java.util.Arrays.asList(positions).indexOf(getPosition())];
            if (this.fastestLap) {
                setPoints(tempPoints + 1);
            } else {
                setPoints(tempPoints);
            }
        }
        else {
            setPoints(0);
        }
    }

    public F1Result() {
    }

    public F1Track getTrack() {
        return track;
    }

    public void setTrack(F1Track track) {
        this.track = track;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }


    public Boolean getFastestLap() {
        return fastestLap;
    }

    public void setFastestLap(Boolean fastestLap) {
        this.fastestLap = fastestLap;
    }

    public Integer getPosition() {
        return position;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public F1Driver getF1Driver() {
        return f1Driver;
    }

    public void setF1Driver(F1Driver driver) {
        this.f1Driver = driver;
    }
}
