package com.jake.f1SeasonTracker.driver;

import jakarta.persistence.*;
import org.apache.commons.codec.binary.Base64;

@Entity
public class F1Driver {

    public F1Driver(){
    }

    public F1Driver(String f1DriverName, String password) {
        this.setPassword(password);
        this.setF1DriverName(f1DriverName);
    }

    @Id
    @Column(name = "driver_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String f1DriverName;
    private String password;

    @Lob
    private byte[] f1DriverLogo;

    public byte[] getF1DriverLogo() {
        return f1DriverLogo;
    }

    public String generateBase64ImageFromLogo()
    {
        return Base64.encodeBase64String(this.getF1DriverLogo());
    }

    public void setF1DriverLogo(byte[] f1DriverLogo) {
        this.f1DriverLogo = f1DriverLogo;
    }

    public F1Driver(String f1DriverName, String password, byte[] f1DriverLogo) {
        this.setPassword(password);
        this.setF1DriverName(f1DriverName);
        this.setF1DriverLogo(f1DriverLogo);
    }

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