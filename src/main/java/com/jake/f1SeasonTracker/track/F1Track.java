package com.jake.f1SeasonTracker.track;

import com.jake.f1SeasonTracker.country.Country;
import jakarta.persistence.*;

import java.io.IOException;

@Entity
public class F1Track {

    @JoinColumn(name = "country_id")
    @ManyToOne
    private Country country;
    private String name;

    @Id
    @Column(name = "track_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public F1Track(){
    }

    public F1Track(String name, String country) {
        this.setCountry(new Country(country));
        this.setName(name);
    }

    public F1Track(String name, Country newCountry) {
        this.setName(name);
        setCountry(newCountry);
    }

    public Integer getId() {
        return id;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Country getCountry() {
        return country;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
