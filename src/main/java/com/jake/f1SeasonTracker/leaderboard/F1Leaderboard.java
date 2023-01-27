package com.jake.f1SeasonTracker.leaderboard;

import java.util.ArrayList;
import java.util.Collections;

public class F1Leaderboard {
    ArrayList<F1LeaderboardEntry> f1LeaderboardEntries;

    public F1Leaderboard() {
        this.f1LeaderboardEntries = new ArrayList<F1LeaderboardEntry>();
    }

    public void addF1LeaderboardEntry(F1LeaderboardEntry entry) {
        f1LeaderboardEntries.add(entry);
        sortLeaderboardEntries();
    }

    public ArrayList<F1LeaderboardEntry> getF1LeaderboardEntries() {
        return f1LeaderboardEntries;
    }

    public void tableFormat() {
        for(F1LeaderboardEntry entry : f1LeaderboardEntries){
            System.out.print(entry.getF1Driver().getF1DriverName() + " Scored " + entry.getTotal() +" Points\n");
        }
    }

    public void sortLeaderboardEntries() {
        Collections.sort(f1LeaderboardEntries);
        for(F1LeaderboardEntry entry : f1LeaderboardEntries){
            entry.setPosition((short) (f1LeaderboardEntries.indexOf(entry)+1));
        }
    }
}
