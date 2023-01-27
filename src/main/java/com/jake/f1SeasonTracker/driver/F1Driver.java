package com.jake.f1SeasonTracker.driver;

import jakarta.persistence.*;

@Entity
public class F1Driver {

    public F1Driver(){
    }

    public F1Driver(String name, String password) {
        this.setPassword(password);
        this.setF1DriverName(name);
    }

    @Id
    @Column(name = "driver_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String f1DriverName;
    private String password;

    public String getF1DriverName() {
        return f1DriverName;
    }

    public void setF1DriverName(String driverName) {
        this.f1DriverName = driverName;
    }

    public Integer getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}