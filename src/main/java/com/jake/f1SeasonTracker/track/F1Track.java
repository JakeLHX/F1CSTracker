package com.jake.f1SeasonTracker.track;

import jakarta.persistence.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Entity
public class F1Track {

    private String country;
    private String name;
    private String flagURL;

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

    public String getFlagURL() {
        return flagURL;
    }

    public void setFlagURL() {
        setFlagURLFromRestAPI();
    }

    private void setFlagURLFromRestAPI() {
        try {
            // Set up the connection
            URL url = new URL("https://restcountries.com/v3.1/name/"+getCountry());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            // Read the response
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            StringBuilder sb = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }

            // Parse the response with JsonPath
            JSONArray jsonArray = new JSONArray(sb.toString());
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            String flagsUrl = jsonObject.getJSONObject("flags").getString("svg");
            this.flagURL = flagsUrl;

            // Clean up
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
