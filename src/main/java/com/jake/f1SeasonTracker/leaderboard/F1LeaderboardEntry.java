package com.jake.f1SeasonTracker.leaderboard;

import com.jake.f1SeasonTracker.result.F1Result;
import com.jake.f1SeasonTracker.driver.F1Driver;

import java.util.ArrayList;

public class F1LeaderboardEntry implements Comparable<F1LeaderboardEntry> {
    F1Driver f1driver;
    Double total = 0.0;
    Short position = 0;

    public F1LeaderboardEntry(F1Driver driver) {
        this.f1driver = driver;
    }

    public void calculateResults(ArrayList<F1Result> results) {
        for (F1Result result : results) {
            total += result.getPoints();
        }
    }

    public F1Driver getF1Driver() {
        return f1driver;
    }

    public Double getTotal() {
        return total;
    }

    public Short getPosition() {
        return position;
    }

    public void setPosition(Short position) {
        this.position = position;
    }

    @Override
    public int compareTo(F1LeaderboardEntry o) {
        return (int)(o.getTotal() - this.getTotal());
    }
}