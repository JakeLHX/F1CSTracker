package com.jake.f1SeasonTracker.track;

import jakarta.persistence.*;

@Entity
public class F1Track {

    private String country;
    private String name;

    @Id
    @Column(name = "track_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public F1Track(){
    }

    public F1Track(String name, String country) {
        this.setCountry(country);
        this.setName(name);
    }

    public Integer getId() {
        return id;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
